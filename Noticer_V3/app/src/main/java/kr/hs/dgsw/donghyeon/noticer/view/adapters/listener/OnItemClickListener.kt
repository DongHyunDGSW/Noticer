package kr.hs.dgsw.donghyeon.noticer.view.adapters.listener

import com.google.firebase.auth.UserInfo
import kr.hs.dgsw.donghyeon.noticer.data.entity.RoomEntity
import kr.hs.dgsw.donghyeon.noticer.data.entity.UserInfoEntity

interface OnItemClickListener {
    fun onClicked(roomData : RoomEntity)
}