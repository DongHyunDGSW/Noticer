package kr.hs.dgsw.donghyeon.noticer.data.entity

data class AcceptedRoomEntity(
    val title : String?= null,
    val content : String?= null,
    val imagePath : String?= null,
    val comment : ArrayList<CommentEntity>
)