package com.simple.noticer.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.simple.data.model.RoomData
import com.simple.data.model.Row

class MainViewModel(application: Application) : AndroidViewModel(application) {
    var roomDataLiveList = MutableLiveData<ArrayList<RoomData>>()
    var schoolDataList = MutableLiveData<ArrayList<Row>>()
}