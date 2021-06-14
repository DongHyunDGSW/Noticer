package kr.hs.dgsw.donghyeon.noticer.viewmodel.fragments

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.androidhuman.rxfirebase2.database.ChildAddEvent
import com.androidhuman.rxfirebase2.database.childEvents
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import io.reactivex.disposables.Disposable
import kr.hs.dgsw.donghyeon.noticer.base.BaseViewModel
import kr.hs.dgsw.donghyeon.noticer.data.entity.NoticeEntity
import kr.hs.dgsw.donghyeon.noticer.data.entity.RoomEntity
import kr.hs.dgsw.donghyeon.noticer.data.entity.UserInfoEntity
import kr.hs.dgsw.donghyeon.noticer.view.adapters.NoticeAdapter
import kr.hs.dgsw.donghyeon.noticer.view.adapters.RoomAdapter
import kr.hs.dgsw.donghyeon.noticer.view.adapters.listener.OnItemClickListener
import kr.hs.dgsw.donghyeon.noticer.view.adapters.listener.OnNoticeClickListener

class RoomViewModel : BaseViewModel() {

    val noticeDataList = MutableLiveData<ArrayList<NoticeEntity>>()
    var hasCompleted = MutableLiveData<Boolean>()
    val checkUserList = ArrayList<String>()
    val isActive = MutableLiveData<Boolean>()

    val noticeItemAdapter = NoticeAdapter(object : OnNoticeClickListener {
        override fun onClicked(data: NoticeEntity) {

        }
    })

    init {
        initializeData()
    }

    fun initUser(roomUid: String) {
        var isCheckedUser = false
        val userUid = FirebaseAuth.getInstance().currentUser!!.uid

        for(i in checkUserList.indices) {
            if(checkUserList[i] == userUid) {
                isCheckedUser = true
                break
            }
        }

        if(!isCheckedUser) {
            ref.child("roomData").child(roomUid)
                .child("roomMember").child(userUid)
                .setValue(userUid)
        }
    }

    private fun initializeData() {
        noticeDataList.value = arrayListOf()
    }

    fun activeClick() {
        isActive.value = true
    }

    fun refreshData(roomUid : String) {
        addDisposable(initObserveNoticeDataList(roomUid))
        addDisposable(initObserveUserList(roomUid))
        initUser(roomUid)
    }

    fun initObserveUserList(roomUid : String): Disposable {
        return ref.child("roomData").child(roomUid).child("roomMember").childEvents()
            .ofType(ChildAddEvent::class.java)
            .doOnSubscribe {
                hasCompleted.value = true
                Log.d("TAG", "Completed")
            }.subscribe({ response ->
                val data = response.dataSnapshot().getValue(String::class.java)

                checkUserList.add(data!!)
                Log.d("TAG", "cc : $checkUserList")
            }) { Log.d("TAG", "${it.message}") }
    }

    fun initObserveNoticeDataList(roomUid : String): Disposable {
        return ref.child("roomData").child(roomUid).child("noticeList").childEvents()
            .ofType(ChildAddEvent::class.java)
            .doOnSubscribe {
                hasCompleted.value = true
                Log.d("TAG", "Completed")
            }.subscribe({ response ->
                val data = response.dataSnapshot().getValue(NoticeEntity::class.java)

                val dataNotice = NoticeEntity(
                    data!!.title,
                    data.content,
                    data.writer,
                    data.imagePath,
                    data.comment
                )

                noticeDataList.value?.add(dataNotice)
                Log.d("TAG", "${noticeDataList.value!!}")
                noticeItemAdapter.setData(noticeDataList.value!!)
            }) { Log.d("TAG", "${it.message}") }
    }
}