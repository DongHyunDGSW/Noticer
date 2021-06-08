package com.simple.noticer.data.module

import androidx.lifecycle.ViewModel
import com.google.firebase.database.*
import com.simple.data.model.RoomData
import com.simple.noticer.viewmodel.MainViewModel
import io.reactivex.Observable
import io.reactivex.observers.DisposableObserver
import kotlinx.coroutines.*
import java.util.*
import kotlin.collections.ArrayList

class FirebaseDBModule {
    companion object {
        var dataRoomList = ArrayList<RoomData>()

        fun getRoomList(viewModel: ViewModel) {

            FirebaseDatabase.getInstance().reference.child("roomData").addChildEventListener(object : ChildEventListener {
                override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                    val roomData = snapshot.getValue(RoomData::class.java)

                    dataRoomList.add(RoomData(roomData!!.roomName, roomData.roomLimited,
                        roomData.roomFounder, roomData.roomContent, roomData.roomPassword, roomData.roomKey))
                }

                override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                }

                override fun onChildRemoved(snapshot: DataSnapshot) {
                }

                override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                }

                override fun onCancelled(error: DatabaseError) {
                }

            })


            Observable.just(dataRoomList)
                .subscribe(object : DisposableObserver<ArrayList<RoomData>>() {
                    override fun onNext(response : ArrayList<RoomData>) {
                        if (viewModel is MainViewModel) {
                            viewModel.roomDataLiveList.value?.run {
                                clear()
                                addAll(response)
                            }
                        }
                    }

                    override fun onError(e: Throwable) {
                    }

                    override fun onComplete() {
                    }
                })
        }

        fun createRoom(roomData: RoomData) {
            FirebaseDatabase.getInstance().reference.child("roomData").push().setValue(roomData)
        }
    }
}