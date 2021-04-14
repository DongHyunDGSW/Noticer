package com.simple.noticer.data.module

import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.simple.noticer.data.model.RoomData
import kotlinx.coroutines.*

class FirebaseDataBaseModule {
    companion object{
            val jobGetRoom : Deferred<ArrayList<RoomData>> = CoroutineScope(Dispatchers.Default).async{
                val dataRoomList = ArrayList<RoomData>()

                dataRoomList.clear()

                val getRoomSentence : Job = GlobalScope.async {
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
        }
}