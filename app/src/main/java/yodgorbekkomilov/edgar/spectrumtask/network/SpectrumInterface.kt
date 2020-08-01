package yodgorbekkomilov.edgar.spectrumtask.network

import retrofit2.Call
import retrofit2.http.GET
import yodgorbekkomilov.edgar.spectrumtask.SpectrumResponse



interface SpectrumInterface {
    @GET("/api/json/get/Vk-LhK44U")
    fun getApi(): Call <SpectrumResponse>
}


