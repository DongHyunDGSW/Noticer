package com.simple.noticer.data.model


data class RoomData(var roomName : String?= null, var roomLimited : Int?= null, var roomFounder : String?= null, var roomContent : ArrayList<BoardData>?= ArrayList(), var roomPassword : String?= null)

data class BoardData(var title : String?= null, var content : String?= null, var writer : String?= null)

data class Result(var MESSAGE : String?= null)

data class Head(var list_total_count : Int?= null, val RESULT : Result?)

data class Row(var ATPT_OFCDC_SC_NM : String?, var SCHUL_NM : String?, var COEDU_SC_NM : String?)

data class SchoolInfo(var head : ArrayList<Head>, var row : ArrayList<Row>)

data class ResSchoolParseData(var schoolInfo : ArrayList<SchoolInfo> ?= ArrayList(), var RESULT: Result?)


data class UserData(var userUid : String?, var profile : String?, var name : String?, var schoolName : String?, var classNum : String?)