package kr.hs.dgsw.donghyeon.noticer.viewmodel.fragments

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.androidhuman.rxfirebase2.database.ChildAddEvent
import com.androidhuman.rxfirebase2.database.childEvents
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
        override fun onClicked(data: UserInfoEntity, roomData : RoomEntity) {
            userInfo.value = data
            roomDataInfo.value = roomData
            actionRVClick.value = true
        }
    })

    val userInfo = MutableLiveData<UserInfoEntity>()
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
            }.subscribe({
                val data = it.dataSnapshot().getValue(RoomEntity::class.java)

                val dataRoom = RoomEntity(
                    data!!.roomFounder,
                    data.roomLimited,
                    data.roomName,
                    data.isPrivate,
                    data.roomPassword,
                    data.roomUid,
                    data.noticeList
                )

                roomDataList.value?.add(dataRoom)
                itemAdapter.setData(roomDataList.value!!)
            }) { Log.d("TAG", "${it.message}") }
    }
    
    private fun addRoomDataList() {
        for(i in 0 until 10) {
            val r = ref.child("roomData").push()
            r.setValue(RoomEntity("$i 선생님",10,"${i}번째 ",false,"", r.key, hashMapOf()))
            sampleNoticeList(r.key!!)
        }
    }

    private fun sampleNoticeList(key : String) {
        for(i in 0 until 21) {
            ref.child("roomData").child(key).child("noticeList").push().setValue(NoticeEntity("$i 제목","$i 번째 글입니다.", "$i 번째 선생님", "", arrayListOf()))
        }
    }
}