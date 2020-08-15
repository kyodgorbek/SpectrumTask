package yodgorbekkomilov.edgar.spectrumtask

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import yodgorbekkomilov.edgar.spectrumtask.adapter.MemberAdapter
import yodgorbekkomilov.edgar.spectrumtask.network.ServiceBuilder
import yodgorbekkomilov.edgar.spectrumtask.network.SpectrumInterface

class MemberActivity : AppCompatActivity() {

    private val memberAdapter: MemberAdapter = MemberAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_member)

        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(
            ServiceBuilder.buildService(SpectrumInterface::class.java).getMembers()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { members -> onResponse(members as MutableList<Member>) },
                    { t -> onFailure(t) })
        )

    }

    private fun onFailure(t: Throwable) {
        Toast.makeText(this, t.message, Toast.LENGTH_SHORT).show()
    }

    private fun onResponse(members: List<Member>) {

        recyclerView.apply {
            setHasFixedSize(true)
            progressBar.visibility = View.GONE
            layoutManager = LinearLayoutManager(this@MemberActivity)
            adapter = memberAdapter


        }
        memberAdapter.setMembers(members)
        memberAdapter.notifyDataSetChanged()
    }


}

