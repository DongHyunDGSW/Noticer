package com.simple.noticer.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.FirebaseDatabase
import com.simple.data.model.BoardData
import com.simple.data.model.RoomData
import com.simple.noticer.data.module.FirebaseDBModule

class MainViewModel(application: Application) : AndroidViewModel(application) {
    var roomDataLiveList = MutableLiveData<ArrayList<RoomData>>()

    fun createRoom() {
        FirebaseDBModule.createRoom(RoomData("",2,"", ArrayList<BoardData>(),"", FirebaseDatabase.getInstance().reference.key))
    }
}