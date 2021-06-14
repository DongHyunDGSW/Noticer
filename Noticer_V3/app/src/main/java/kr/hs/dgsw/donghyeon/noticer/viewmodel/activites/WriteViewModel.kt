package kr.hs.dgsw.donghyeon.noticer.viewmodel.activites

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.androidhuman.rxfirebase2.database.ChildEvent
import com.androidhuman.rxfirebase2.database.childEvents
import com.androidhuman.rxfirebase2.database.rxSetValue
import io.reactivex.disposables.Disposable
import kr.hs.dgsw.donghyeon.noticer.base.BaseViewModel
import kr.hs.dgsw.donghyeon.noticer.data.entity.NoticeEntity
import kr.hs.dgsw.donghyeon.noticer.data.entity.UserInfoEntity

class WriteViewModel : BaseViewModel() {
    val title = MutableLiveData<String>()
    val content = MutableLiveData<String>()
    val userName = MutableLiveData<String>()
    val roomUid = MutableLiveData<String>()
    val isCompleted = MutableLiveData<Boolean>()

    init {
        addDisposable(initGetUserName())
    }

    fun writeNotice() {
        addDisposable(disposableWriteNotice(roomUid.value!!))
    }

    fun disposableWriteNotice(roomUid : String) : Disposable {
        return ref.child("roomData").child(roomUid)
            .child("noticeList").push()
            .rxSetValue(NoticeEntity(title.value!!,content.value!!, userName.value!!,"", arrayListOf())).subscribe {
                isCompleted.value = true
            }
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