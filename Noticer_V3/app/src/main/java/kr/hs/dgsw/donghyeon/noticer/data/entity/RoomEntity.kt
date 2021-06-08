package kr.hs.dgsw.donghyeon.noticer.data.entity

data class RoomEntity(
    val roomFounder : String?= null,
    val roomLimited : Int?= null,
    val roomName : String?= null,
    val isPrivate : Boolean?= false,
    val roomPassword : String?= null
)