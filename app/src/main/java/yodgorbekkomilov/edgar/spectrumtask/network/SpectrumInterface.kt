package yodgorbekkomilov.edgar.spectrumtask.network

import retrofit2.Call
import retrofit2.http.GET
import yodgorbekkomilov.edgar.spectrumtask.SpectrumResponse



interface SpectrumInterface {
    @GET("/15c43d65bc7a989f47f1")
    fun getApi(): Call <SpectrumResponse>
}


