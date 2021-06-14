package kr.hs.dgsw.donghyeon.noticer.viewmodel.fragments

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.androidhuman.rxfirebase2.database.ChildAddEvent
import com.androidhuman.rxfirebase2.database.ChildEvent
import com.androidhuman.rxfirebase2.database.childEvents
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.disposables.Disposable
import kr.hs.dgsw.donghyeon.noticer.base.BaseViewModel
import kr.hs.dgsw.donghyeon.noticer.data.entity.NoticeEntity
import kr.hs.dgsw.donghyeon.noticer.data.entity.RoomEntity
import kr.hs.dgsw.donghyeon.noticer.data.entity.UserInfoEntity
import kr.hs.dgsw.donghyeon.noticer.view.adapters.RoomAdapter
import kr.hs.dgsw.donghyeon.noticer.view.adapters.listener.OnItemClickListener
import kr.hs.dgsw.donghyeon.noticer.widget.extensions.convertToArray

class HomeViewModel : BaseViewModel() {
    val roomDataList = MutableLiveData<ArrayList<RoomEntity>>()
    var hasCompleted = MutableLiveData<Boolean>()


    val actionRVClick = MutableLiveData<Boolean>()
    val itemAdapter = RoomAdapter(object : OnItemClickListener {
        override fun onClicked(roomData : RoomEntity) {
            roomDataInfo.value = roomData
            actionRVClick.value = true
        }
    })

    val roomDataInfo = MutableLiveData<RoomEntity>()

    init {
        addDisposable(initRoomData())
        addDisposable(initRoomUidList())
    }

    fun initRoomUidList() : Disposable {
        return ref.child("roomData").childEvents()
            .ofType(ChildAddEvent::class.java)
            .subscribe({ response ->
                val data = response.dataSnapshot().getValue(RoomEntity::class.java)

                Log.d("TAG", "${data?.roomUid}")
                addDisposable(initObserveUserList(data?.roomUid!!))
            }) { Log.d("TAG ERR", "${it.message}") }
    }

    private fun initObserveUserList(roomUid : String): Disposable {
        return ref.child("roomData").child(roomUid).child("roomMember").childEvents()
            .ofType(ChildAddEvent::class.java)
            .doOnSubscribe {
                hasCompleted.value = true
                Log.d("TAG", "Completed")
            }.subscribe({ response ->
                val data = response.dataSnapshot().getValue(String::class.java)

                if(FirebaseAuth.getInstance().currentUser?.uid == data!!) {
                    Log.d("TAGLOL", "${FirebaseAuth.getInstance().currentUser?.uid == data}, $data")
                }

            }) { Log.d("TAG ERR OB", "${it.message}") }
    }

    private fun initRoomData() : Disposable {
        return ref.child("roomData").childEvents()
            .ofType(ChildAddEvent::class.java)
            .doOnSubscribe {
                Log.d("TAG", "onComplete")
                hasCompleted.value = true
            }.subscribe({ response ->
                val data = response.dataSnapshot().getValue(RoomEntity::class.java)

                val dataRoom = RoomEntity(
                    data!!.roomFounder,
                    data.roomLimited,
                    data.roomName,
                    data.isPrivate,
                    data.roomPassword,
                    data.roomUid,
                    data.noticeList,
                    data.roomMember
                )

                roomDataList.value?.add(dataRoom)
                itemAdapter.setData(roomDataList.value!!)
            }) { Log.d("TAG", "${it.message}") }
    }
}