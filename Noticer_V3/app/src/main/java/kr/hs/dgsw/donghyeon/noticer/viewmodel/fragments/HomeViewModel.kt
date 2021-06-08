package kr.hs.dgsw.donghyeon.noticer.viewmodel.fragments

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.androidhuman.rxfirebase2.database.ChildAddEvent
import com.androidhuman.rxfirebase2.database.childEvents
import io.reactivex.disposables.Disposable
import kr.hs.dgsw.donghyeon.noticer.base.BaseViewModel
import kr.hs.dgsw.donghyeon.noticer.data.entity.RoomEntity
import kr.hs.dgsw.donghyeon.noticer.view.adapters.RoomAdapter

class HomeViewModel : BaseViewModel() {

    val roomDataList = MutableLiveData<ArrayList<RoomEntity>>()
    val itemAdapter = MutableLiveData<RoomAdapter>()
    var hasCompleted = MutableLiveData<Boolean>()

    val getItemAdapter : RoomAdapter
        get() = itemAdapter.value!!

    init {
        initializeData()
        initializeObserver()
    }

    private fun initializeData() {
        roomDataList.value = arrayListOf()
        itemAdapter.value = RoomAdapter()
    }

    private fun initializeObserver() {
        addDisposable(initObserveRoomDataList())
    }

    fun refreshData() {
        addDisposable(refreshObserveRoomData())
    }

    private fun refreshObserveRoomData(): Disposable {
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
                    data.roomPassword
                )

                roomDataList.value?.add(dataRoom)
                Log.d("TAG", "${roomDataList.value}")
                itemAdapter.value?.setData(roomDataList.value!!)
            }) { Log.d("TAG", "${it.message}") }
    }

    private fun initObserveRoomDataList(): Disposable {
        return ref.child("roomData").childEvents()
            .ofType(ChildAddEvent::class.java)
            .subscribe({
                val data = it.dataSnapshot().getValue(RoomEntity::class.java)

                val dataRoom = RoomEntity(
                    data!!.roomFounder,
                    data.roomLimited,
                    data.roomName,
                    data.isPrivate,
                    data.roomPassword
                )

                roomDataList.value?.add(dataRoom)
                Log.d("TAG", "${roomDataList.value}")
                itemAdapter.value?.setData(roomDataList.value!!)
            }) { Log.d("TAG", "${it.message}") }
    }
}