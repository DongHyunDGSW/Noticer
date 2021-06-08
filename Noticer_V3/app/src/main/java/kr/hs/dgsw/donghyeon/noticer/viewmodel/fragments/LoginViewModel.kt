package kr.hs.dgsw.donghyeon.noticer.viewmodel.fragments

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import kr.hs.dgsw.donghyeon.noticer.base.BaseViewModel

class LoginViewModel : BaseViewModel() {

    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val isLoginCompleted = MutableLiveData<Boolean>()
    val registerClickEvent = MutableLiveData<Boolean>()


    fun signIn() {
        if(!email.value.isNullOrEmpty() && !password.value.isNullOrEmpty()) {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email.value!!, password.value!!).addOnCompleteListener { task ->
                if(task.isSuccessful) {
                    Log.d("TAG", "onSuccessful")
                    isLoginCompleted.value = true
                }else{
                    Log.d("TAG", "onFailed")
                }
            }
        }
    }

    fun toSignUp() {
        registerClickEvent.value = true
    }
}