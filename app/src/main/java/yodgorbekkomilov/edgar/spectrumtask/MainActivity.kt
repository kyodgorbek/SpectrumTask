package yodgorbekkomilov.edgar.spectrumtask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import yodgorbekkomilov.edgar.spectrumtask.adapter.SpectrumAdapter
import yodgorbekkomilov.edgar.spectrumtask.network.ServiceBuilder
import yodgorbekkomilov.edgar.spectrumtask.network.SpectrumInterface

class MainActivity : AppCompatActivity() {

    companion object{
        val TAG:String="No Data available"
    }

    var  logo:String? = null
    var companyName:String? =  null
     var companyWebsite:String? = null
     var  spectrumResponse: SpectrumResponse? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val request = ServiceBuilder.buildService(SpectrumInterface::class.java)
        val call = request.getApi()

        call.enqueue(object : Callback<SpectrumResponse> {
            override fun onResponse(call: Call<SpectrumResponse>, response: Response<SpectrumResponse>) {
                Log.d("Not data showing","Not Showing")
                if (response.isSuccessful){
                  logo  = response.body()?.get(2)?.logo!!
                  companyName = response?.body()?.get(1)?.company!!
                  companyWebsite  =  response?.body()?.get(2)?.website!!

                    recyclerView.apply {
                        setHasFixedSize(true)
                        progressBar.visibility = View.GONE
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        adapter = spectrumResponse?.let { SpectrumAdapter(it,
                            logo!!, companyName!!, companyWebsite!!
                        ) }
                    }
                }
            }


            override fun onFailure(call: Call<SpectrumResponse>, t: Throwable) {
               Log.e("Error", t.message)
            }
        })
    }

    }
