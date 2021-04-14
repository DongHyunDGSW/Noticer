package com.simple.noticer.data.net

import com.simple.noticer.data.model.ResSchoolParseData
import com.simple.noticer.data.module.ParseSchoolModule
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET
import retrofit2.http.Query

interface Network {
    @GET("schoolInfo")
    fun getAllSchools(@Query("KEY") KEY : String?, @Query("Type") Type : String = "json",@Query("SCHUL_NM") SchoolName : String = "") : Call<ResSchoolParseData>
}