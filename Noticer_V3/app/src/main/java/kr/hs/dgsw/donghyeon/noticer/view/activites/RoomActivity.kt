package kr.hs.dgsw.donghyeon.noticer.view.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.navArgs
import kr.hs.dgsw.donghyeon.noticer.R
import kr.hs.dgsw.donghyeon.noticer.base.BaseActivity
import kr.hs.dgsw.donghyeon.noticer.data.entity.NoticeEntity
import kr.hs.dgsw.donghyeon.noticer.databinding.ActivityRoomBinding
import kr.hs.dgsw.donghyeon.noticer.view.adapters.NoticeAdapter
import kr.hs.dgsw.donghyeon.noticer.view.adapters.listener.OnNoticeClickListener
import kr.hs.dgsw.donghyeon.noticer.viewmodel.fragments.RoomViewModel
import kr.hs.dgsw.donghyeon.noticer.widget.extensions.setSupportCollapsingToolbar

class RoomActivity : BaseActivity<ActivityRoomBinding, RoomViewModel>() {
    override val layoutRes: Int
        get() = R.layout.activity_room
    override val viewModel: RoomViewModel
        get() = ViewModelProvider(this)[RoomViewModel::class.java]

    override fun onCreatedView(view: ActivityRoomBinding) {

        val roomActivityArgs : RoomActivityArgs by navArgs()

        with(viewModel) {
            view.swipeLayout.setOnRefreshListener {
                noticeDataList.value?.clear()
                refreshData(roomActivityArgs.roomUid!!)
            }

            val roomUid = roomActivityArgs.roomUid!!

            setSupportCollapsingToolbar(view.toolbar, view.collapsingToolbar, roomActivityArgs.roomTitle!!)

            initObserveNoticeDataList(roomUid)
            addDisposable(initObserveUserList(roomUid))
            initUser(roomUid)
        }
    }

}