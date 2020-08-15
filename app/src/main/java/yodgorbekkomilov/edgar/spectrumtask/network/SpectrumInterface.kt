package yodgorbekkomilov.edgar.spectrumtask.network

import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import yodgorbekkomilov.edgar.spectrumtask.RootItem
import yodgorbekkomilov.edgar.spectrumtask.SpectrumResponse

interface SpectrumInterface {
    @GET("15c43d65bc7a989f47f1")
    fun getApi(): Observable<SpectrumResponse>

    @GET("15c43d65bc7a989f47f1")
    fun getMembers(): Single<List<RootItem>>

}


