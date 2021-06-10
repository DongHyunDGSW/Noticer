package kr.hs.dgsw.donghyeon.noticer.network

import io.reactivex.Single
import kr.hs.dgsw.donghyeon.noticer.base.constants.Constants
import kr.hs.dgsw.donghyeon.noticer.data.entity.ResultSchoolEntity
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface SchoolAPI {
    @GET("schoolInfo")
    fun getSchoolInfo(@Query("KEY") key : String?,
                      @Query("Type") type : String = "json",
                      @Query("SCHUL_NM") schoolName : String = "") : Single<retrofit2.Response<ResultSchoolEntity>>

    companion object {
        fun create() : SchoolAPI?{
            return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(SchoolAPI::class.java)
        }
    }
}