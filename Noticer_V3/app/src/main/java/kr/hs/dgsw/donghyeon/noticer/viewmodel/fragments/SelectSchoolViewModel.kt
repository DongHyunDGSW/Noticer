package kr.hs.dgsw.donghyeon.noticer.viewmodel.fragments

import androidx.lifecycle.MutableLiveData
import kr.hs.dgsw.donghyeon.noticer.base.BaseViewModel

class SelectSchoolViewModel : BaseViewModel() {

    val actionToInfoInput = MutableLiveData<Boolean>()

    fun actionToInfo() {
        actionToInfoInput.value = true
    }
}