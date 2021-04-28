package com.simple.noticer.ui.adapter.listener

import com.simple.noticer.data.model.RoomData
import com.simple.noticer.data.model.Row

interface onClickItemListener {
    fun onClickItemListener(position : Int, data : RoomData)
}

interface onSchoolItemListener {
    fun onClickItemListener(data : Row)
}