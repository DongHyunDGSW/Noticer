package com.simple.domain.repository

import com.simple.domain.models.Result
import com.simple.domain.models.RoomData
import io.reactivex.Single


interface RoomRepository {
    fun getRoomResultList() : Single<ArrayList<Result<RoomData>>>
}