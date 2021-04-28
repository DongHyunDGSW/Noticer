package com.simple.domain.models

data class RoomData(var roomName : String?= null, var roomLimited : Int?= null, var roomFounder : String?= null, var roomContent : ArrayList<BoardData>?= ArrayList(), var roomPassword : String?= null)
