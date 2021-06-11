package kr.hs.dgsw.donghyeon.noticer.viewmodel.fragments

import android.app.Application
import android.util.Log
import android.widget.ArrayAdapter
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import kr.hs.dgsw.donghyeon.noticer.R
import kr.hs.dgsw.donghyeon.noticer.base.BaseAndroidViewModel
import kr.hs.dgsw.donghyeon.noticer.data.entity.UserInfoEntity

class InputInfoViewModel(application: Application) : BaseAndroidViewModel(application) {

    val selectedData = MutableLiveData<String>()

    val classNum = MutableLiveData<String>()
    val name = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val schoolName = MutableLiveData<String>()
    val isLoginCompleted = MutableLiveData<Boolean>()
    val passwordCheck = MutableLiveData<String>()

    val arrayAdapter = ArrayAdapter.createFromResource(application.applicationContext,
                                R.array.menu_gender, android.R.layout.simple_spinner_item)

    val isRegisterCompleted = MutableLiveData<Boolean>()

    fun signUpData(userInfo: UserInfoEntity) {
        ref.child("userInfo").
                    child(FirebaseAuth.getInstance().currentUser?.uid!!)
                            .setValue(UserInfoEntity(userInfo.userName, userInfo.userToken,
                                userInfo.userClassNum, userInfo.userSchoolName, userInfo.inRoom))

        Log.d("TAG", "$userInfo")
    }

    fun signUp() {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email.value!!, password.value!!).addOnCompleteListener { task ->
                if(task.isSuccessful) {
                    Log.d("TAG", "onCreated")
                    isRegisterCompleted.value = true
                    signIn()
                }else{
                    Log.d("TAG","onCreateFailed ${task.exception?.message}")
                }
            }
    }

    private fun signIn() {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email.value!!, password.value!!).addOnCompleteListener { task ->
                if(task.isSuccessful) {
                    Log.d("TAG", "onSuccessful")
                    signUpData(UserInfoEntity(name.value,
                        FirebaseAuth.getInstance().currentUser?.uid!!,classNum.value,
                        schoolName.value, arrayListOf()))
                    isLoginCompleted.value = true
                }else{
                    Log.d("TAG", "onFailed")
                }
            }
    }
}
