package kr.hs.dgsw.donghyeon.noticer.viewmodel.fragments

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.androidhuman.rxfirebase2.database.ChildEvent
import com.androidhuman.rxfirebase2.database.childEvents
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import io.reactivex.disposables.Disposable
import kr.hs.dgsw.donghyeon.noticer.base.BaseViewModel
import kr.hs.dgsw.donghyeon.noticer.data.entity.UserInfoEntity

class AccountViewModel : BaseViewModel() {
    val userName = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val isLogOut = MutableLiveData<Boolean>()

    init {
        initializeAuth()
    }

    private fun initializeAuth() {
        val authData = FirebaseAuth.getInstance().currentUser

        addDisposable(initGetUserName())
        email.value = authData?.email
    }

    fun logOutUser() {
        FirebaseAuth.getInstance().signOut()
        isLogOut.value = true
    }

    private fun initGetUserName() : Disposable{
        return ref.child("userInfo").childEvents()
            .ofType(ChildEvent::class.java)
            .doOnComplete {

            }.subscribe({ response ->
                val data = response.dataSnapshot().getValue(UserInfoEntity::class.java)

                val dataUserInfoEntity =
                    UserInfoEntity(
                        data!!.userName,
                        data.userToken,
                        data.userClassNum,
                        data.userSchoolName,
                        data.inRoom
                    )

                userName.value = dataUserInfoEntity.userName
            }) { error -> Log.d("TAG", "${error.message}") }
    }

}