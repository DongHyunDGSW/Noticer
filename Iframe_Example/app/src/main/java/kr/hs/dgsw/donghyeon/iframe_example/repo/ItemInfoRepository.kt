package kr.hs.dgsw.donghyeon.iframe_example.repo

import io.reactivex.Single
import kr.hs.dgsw.donghyeon.data.DataModel
import kr.hs.dgsw.donghyeon.data.RequestModel
import kr.hs.dgsw.donghyeon.iframe_example.network.Network

class ItemInfoRepository {
    fun getItemInfo(body : RequestModel) : Single<retrofit2.Response<DataModel>> =  Network.serverAPI().getItemInfo(body)
}