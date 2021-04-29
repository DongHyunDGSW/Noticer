package com.simple.noticer.data.module

import androidx.lifecycle.ViewModel
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.simple.data.model.RoomData
import com.simple.noticer.viewmodel.MainViewModel
import io.reactivex.Observable
import io.reactivex.observers.DisposableObserver
import kotlinx.coroutines.*

class FirebaseDataBaseModule {
    companion object {
        var dataRoomList = ArrayList<RoomData>()

        val jobGetRoom: Deferred<ArrayList<RoomData>> = CoroutineScope(Dispatchers.Default).async {
            dataRoomList.clear()

            val getRoomSentence: Job = GlobalScope.async {
                FirebaseDatabase.getInstance().reference.child("roomData").addChildEventListener(object : ChildEventListener {
                    override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                        val roomData = snapshot.getValue(RoomData::class.java)
                        dataRoomList.add(RoomData(roomData!!.roomName, roomData.roomLimited,
                                roomData.roomFounder, roomData.roomContent, roomData.roomPassword))
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
            }

            getRoomSentence.join()

            dataRoomList
        }

        suspend fun getRoomList(viewModel: ViewModel) {

            dataRoomList = jobGetRoom.await()

            withContext(Dispatchers.Main) {
                Observable.just(dataRoomList)
                        .subscribe(object : DisposableObserver<ArrayList<RoomData>>() {
                            override fun onNext(t: ArrayList<RoomData>) {
                                if (viewModel is MainViewModel) {
                                    viewModel.roomDataLiveList.value = t
                                }
                            }

                            override fun onError(e: Throwable) {
                            }

                            override fun onComplete() {
                            }
                        })
            }
        }

        fun createRoom(roomData: RoomData) {
            FirebaseDatabase.getInstance().reference.child("roomData").push().setValue(roomData)
        }
    }
}