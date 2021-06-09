package kr.hs.dgsw.donghyeon.noticer.view.adapters.listener

import kr.hs.dgsw.donghyeon.noticer.data.entity.NoticeEntity

interface OnNoticeClickListener {
    fun onClicked(data : NoticeEntity)
}