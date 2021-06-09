package kr.hs.dgsw.donghyeon.noticer.data.entity

data class NoticeEntity(
    val title : String?= null,
    val content : String?= null,
    val writer : String?= null,
    val imagePath : String?= null,
    val comment : ArrayList<CommentEntity>?= null
)