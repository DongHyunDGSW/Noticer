package kr.hs.dgsw.donghyeon.iframe_example.network

import io.reactivex.Single
import kr.hs.dgsw.donghyeon.data.DataModel
import kr.hs.dgsw.donghyeon.data.RequestModel
import retrofit2.http.Body
import retrofit2.http.POST

interface ItemInfoAPI {
    @POST("product_v_1_5")
    fun getItemInfo(@Body body : RequestModel) : Single<retrofit2.Response<DataModel>>
}