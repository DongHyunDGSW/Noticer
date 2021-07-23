package kr.hs.dgsw.donghyeon.iframe_example.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object Network {
    fun serverAPI() : ItemInfoAPI {
        return Retrofit.Builder()
            .baseUrl("http://52.78.112.23:5000/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ItemInfoAPI::class.java)
    }
}