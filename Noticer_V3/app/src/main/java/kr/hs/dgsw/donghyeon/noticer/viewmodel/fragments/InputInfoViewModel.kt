package kr.hs.dgsw.donghyeon.noticer.viewmodel.fragments

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import kr.hs.dgsw.donghyeon.noticer.base.BaseViewModel

class InputInfoViewModel : BaseViewModel() {
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val passwordCheck = MutableLiveData<String>()

    val isRegisterCompleted = MutableLiveData<Boolean>()

    fun signUp() {
        if(!email.value.isNullOrEmpty() &&
            !password.value.isNullOrEmpty()
            && password.value == passwordCheck.value) {

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email.value!!, password.value!!).addOnCompleteListener { task ->
                if(task.isSuccessful) {
                    Log.d("TAG", "onCreated")
                    isRegisterCompleted.value = true
                }else{
                    Log.d("TAG","onCreateFailed")
                }
            }

        }
    }
}