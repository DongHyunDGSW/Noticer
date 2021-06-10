package kr.hs.dgsw.donghyeon.noticer.viewmodel.fragments

import android.util.Log
import androidx.lifecycle.MutableLiveData
import io.reactivex.Single
import io.reactivex.observers.DisposableSingleObserver
import kr.hs.dgsw.donghyeon.noticer.base.BaseViewModel
import kr.hs.dgsw.donghyeon.noticer.data.entity.ResultSchoolEntity
import kr.hs.dgsw.donghyeon.noticer.data.entity.Row
import kr.hs.dgsw.donghyeon.noticer.repo.repository.SchoolRepository
import kr.hs.dgsw.donghyeon.noticer.view.adapters.SchoolAdapter
import kr.hs.dgsw.donghyeon.noticer.view.adapters.listener.OnSchoolItemClickListener
import retrofit2.Response

class SelectSchoolViewModel : BaseViewModel() {

    val repo = SchoolRepository()
    val actionToInfoInput = MutableLiveData<Boolean>()
    val selectedData = MutableLiveData<Row>()
    val selectSchoolItemAdapter = SchoolAdapter(object : OnSchoolItemClickListener {
        override fun onClick(data: Row) {
            selectedData.value = data
            actionToInfo()
        }
    })

    fun actionToInfo() {
        actionToInfoInput.value = true
    }

    init {
        getSchools()
    }

    fun getSchools(query : String) {
        addDisposable(repo.parseSchool(query),
            object : DisposableSingleObserver<retrofit2.Response<ResultSchoolEntity>>() {
                override fun onSuccess(response : Response<ResultSchoolEntity>) {
                    if(response.isSuccessful) {
                        Log.d("TAG", "${response.body()}")
                        selectSchoolItemAdapter.setData(response.body()!!.schoolInfo!![1].row)
                    }
                }

                override fun onError(e: Throwable) {
                    Log.d("TAG", "${e.message}")
                }
            })
    }

    private fun getSchools() {
        addDisposable(repo.parseSchool(),
            object : DisposableSingleObserver<retrofit2.Response<ResultSchoolEntity>>() {
                override fun onSuccess(response : Response<ResultSchoolEntity>) {
                    if(response.isSuccessful) {
                        Log.d("TAG", "${response.body()}")
                        selectSchoolItemAdapter.setData(response.body()!!.schoolInfo!![1].row)
                    }
                }

                override fun onError(e: Throwable) {
                    Log.d("TAG", "${e.message}")
                }
            })
    }
}