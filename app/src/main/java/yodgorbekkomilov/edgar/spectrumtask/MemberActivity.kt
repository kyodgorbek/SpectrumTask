package yodgorbekkomilov.edgar.spectrumtask

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.progressBar
import kotlinx.android.synthetic.main.activity_main.recyclerView
import kotlinx.android.synthetic.main.activity_main.searchView
import kotlinx.android.synthetic.main.activity_member.*
import yodgorbekkomilov.edgar.spectrumtask.adapter.MemberAdapter
import yodgorbekkomilov.edgar.spectrumtask.network.ServiceBuilder
import yodgorbekkomilov.edgar.spectrumtask.network.SpectrumInterface


class MemberActivity : AppCompatActivity() {
    private val compositeDisposable by lazy { CompositeDisposable() }
    private val buildService by lazy { ServiceBuilder.buildService(SpectrumInterface::class.java) }
    private var memberAdapter: MemberAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_member)


        compositeDisposable.add(buildService
            .getMembers()
            .toObservable()
            .flatMap { Observable.fromIterable(it) }
            .flatMap { Observable.fromIterable(it.members) }
            .toList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { if (compositeDisposable.isDisposed.not()) onResponse(it) },
                { onFailure(it) }
            ))

        recyclerView.adapter = MemberAdapter()
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayout.VERTICAL))

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                memberAdapter?.getFilter()?.filter(newText)

                return true
            }

        })
        ascendingButton.setOnClickListener {
            memberAdapter?.sortAscending()
        }

        descendingButton.setOnClickListener {
            memberAdapter?.sortDescending()
        }


    }

    private fun onFailure(t: Throwable) {
        Toast.makeText(this, t.message, Toast.LENGTH_SHORT).show()
    }

    private fun onResponse(members: List<Member>) {
        progressBar.visibility = View.GONE
        (recyclerView.adapter as MemberAdapter).setMembers(members)
    }

    override fun onPause() {
        super.onPause()
        compositeDisposable.dispose()
    }
}



















