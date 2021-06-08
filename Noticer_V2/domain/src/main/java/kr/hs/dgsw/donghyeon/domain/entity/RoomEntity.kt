package kr.hs.dgsw.donghyeon.domain.entity

data class RoomEntity(
    var roomFounder : String? = null,
    var roomLimited : Int? = null,
    var roomName : String? = null,
    var roomPassword : String? = null
)