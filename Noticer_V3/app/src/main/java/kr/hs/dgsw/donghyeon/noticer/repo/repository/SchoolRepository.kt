package kr.hs.dgsw.donghyeon.noticer.repo.repository

import io.reactivex.Single
import kr.hs.dgsw.donghyeon.noticer.base.constants.Constants
import kr.hs.dgsw.donghyeon.noticer.data.entity.ResultSchoolEntity
import kr.hs.dgsw.donghyeon.noticer.network.SchoolAPI
import retrofit2.create

class SchoolRepository {
    private val api = SchoolAPI.create()

    fun parseSchool() : Single<retrofit2.Response<ResultSchoolEntity>> = api!!.getSchoolInfo(Constants.KEY)

    fun parseSchool(query : String) : Single<retrofit2.Response<ResultSchoolEntity>> = api!!.getSchoolInfo(Constants.KEY, schoolName = query)
}