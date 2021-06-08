package com.simple.data.net

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitModule {
    private var instance : Retrofit?= null

    fun getInstance() : Retrofit {
        if(instance == null) {
            instance = Retrofit.Builder()
                .baseUrl("https://open.neis.go.kr/hub/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        return instance!!
    }
}