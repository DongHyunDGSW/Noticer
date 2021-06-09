package kr.hs.dgsw.donghyeon.noticer.widget.extensions

import kr.hs.dgsw.donghyeon.noticer.data.entity.NoticeEntity

fun convertToArray(hashMap: HashMap<String, NoticeEntity>) : ArrayList<NoticeEntity> {
    return ArrayList(hashMap.values)
}