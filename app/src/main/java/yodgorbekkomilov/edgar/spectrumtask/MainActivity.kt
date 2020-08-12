package yodgorbekkomilov.edgar.spectrumtask

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import yodgorbekkomilov.edgar.spectrumtask.adapter.SpectrumAdapter
import yodgorbekkomilov.edgar.spectrumtask.network.ServiceBuilder
import yodgorbekkomilov.edgar.spectrumtask.network.SpectrumInterface

class MainActivity : AppCompatActivity() {

    private var spectrumAdapter: SpectrumAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(
            ServiceBuilder.buildService(SpectrumInterface::class.java).getApi()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { spectrumResponse -> onResponse(spectrumResponse) },
                    { t -> onFailure(t) })
        )

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                spectrumAdapter?.getFilter()?.filter(newText)

                return true
            }
        })

        ascendingButton.setOnClickListener {
            spectrumAdapter?.sortAscending()
        }

        descendingButton.setOnClickListener {
            spectrumAdapter?.sortDescending()
        }

    }


    private fun onFailure(t: Throwable) {
        Toast.makeText(this, t.message, Toast.LENGTH_SHORT).show()
    }   //  val call = request.getApi()


    private fun onResponse(spectrumResponse: SpectrumResponse) {

        spectrumAdapter = spectrumResponse?.let {
            SpectrumAdapter(it)
        }

        progressBar.visibility = View.GONE
        recyclerView.apply {
            setHasFixedSize(true)

            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = spectrumAdapter
        }
    }


}












