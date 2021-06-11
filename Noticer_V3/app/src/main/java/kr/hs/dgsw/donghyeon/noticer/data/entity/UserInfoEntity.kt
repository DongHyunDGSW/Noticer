package kr.hs.dgsw.donghyeon.noticer.data.entity

data class UserInfoEntity(
    val userName : String?= null,
    val userToken : String?= null,
    val userClassNum : String?= null,
    val userSchoolName : String?= null,
    val inRoom : ArrayList<RoomEntity>?= null
)