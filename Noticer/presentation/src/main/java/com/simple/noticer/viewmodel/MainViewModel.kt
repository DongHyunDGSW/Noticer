package com.simple.noticer.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.simple.noticer.data.model.ResSchoolParseData
import com.simple.noticer.data.model.RoomData
import com.simple.noticer.data.model.Row

class MainViewModel(application: Application) : AndroidViewModel(application) {
    var roomDataLiveList = MutableLiveData<ArrayList<RoomData>>()
    var schoolDataList = MutableLiveData<ArrayList<Row>>()
}