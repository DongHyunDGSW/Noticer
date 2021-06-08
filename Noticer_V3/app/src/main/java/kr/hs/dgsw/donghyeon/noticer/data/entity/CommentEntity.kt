package kr.hs.dgsw.donghyeon.noticer.data.entity

data class CommentEntity(
    val userName : String?= null,
    val userToken : String?= null,
    val commentContent : String?= null
)