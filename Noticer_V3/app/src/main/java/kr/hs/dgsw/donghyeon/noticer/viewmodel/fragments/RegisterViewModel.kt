package kr.hs.dgsw.donghyeon.noticer.viewmodel.fragments

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import kr.hs.dgsw.donghyeon.noticer.base.BaseViewModel

class RegisterViewModel : BaseViewModel() {

    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val passwordCheck = MutableLiveData<String>()

    val actionToSelectSchool = MutableLiveData<Boolean>()

    fun actionToSelectSchool() {
        actionToSelectSchool.value = true
    }
}