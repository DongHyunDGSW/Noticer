package kr.hs.dgsw.donghyeon.noticer.data.entity

data class Result(var MESSAGE : String?= null)

data class Head(var list_total_count : Int?= null, val RESULT : Result?)

data class Row(var ATPT_OFCDC_SC_NM : String?, var SCHUL_NM : String?, var COEDU_SC_NM : String?)

data class SchoolInfo(var head : ArrayList<Head>, var row : ArrayList<Row>)

data class ResultSchoolEntity(var schoolInfo : ArrayList<SchoolInfo> ?= ArrayList(), var RESULT: Result?)
