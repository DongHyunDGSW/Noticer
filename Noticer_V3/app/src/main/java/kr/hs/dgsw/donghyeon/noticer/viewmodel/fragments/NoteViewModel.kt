package kr.hs.dgsw.donghyeon.noticer.viewmodel.fragments

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.androidhuman.rxfirebase2.database.ChildAddEvent
import com.androidhuman.rxfirebase2.database.childEvents
import io.reactivex.disposables.Disposable
import kr.hs.dgsw.donghyeon.noticer.base.BaseViewModel
import kr.hs.dgsw.donghyeon.noticer.data.entity.NoticeEntity
import kr.hs.dgsw.donghyeon.noticer.data.entity.RoomEntity
import kr.hs.dgsw.donghyeon.noticer.data.entity.UserInfoEntity
import kr.hs.dgsw.donghyeon.noticer.view.adapters.RoomAdapter
import kr.hs.dgsw.donghyeon.noticer.view.adapters.listener.OnItemClickListener

class NoteViewModel : BaseViewModel() {
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
        initializeData()
        initializeObserver()
    }

    private fun initializeData() {
        roomDataList.value = arrayListOf()
    }

    private fun initializeObserver() {
        addDisposable(initObserveRoomDataList())
    }

    fun refreshData() {
        addDisposable(initObserveRoomDataList())
    }

    private fun initObserveRoomDataList(): Disposable {
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