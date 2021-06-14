package kr.hs.dgsw.donghyeon.noticer.data.entity

data class RoomEntity(
    val roomFounder : String?= null,
    val roomLimited : Int?= null,
    val roomName : String?= null,
    val isPrivate : Boolean?= false,
    val roomPassword : String?= null,
    val roomUid : String?= null,
    val noticeList : HashMap<String, NoticeEntity>?= null,
    val roomMember : HashMap<String, String>?= null
)