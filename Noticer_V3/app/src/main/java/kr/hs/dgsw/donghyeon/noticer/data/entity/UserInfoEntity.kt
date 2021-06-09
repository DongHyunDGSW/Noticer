package kr.hs.dgsw.donghyeon.noticer.data.entity

data class UserInfoEntity(
    val userName : String?= null,
    val userToken : String?= null,
    val inRoom : ArrayList<NoticeEntity>?
)