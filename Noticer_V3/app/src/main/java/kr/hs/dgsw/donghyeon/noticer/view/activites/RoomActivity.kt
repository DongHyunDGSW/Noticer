package kr.hs.dgsw.donghyeon.noticer.view.activites

import android.content.Intent
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

        initRoomList(roomActivityArgs.roomUid!!)
        setSupportCollapsingToolbar(view.toolbar, view.collapsingToolbar, roomActivityArgs.roomTitle!!)

        with(viewModel) {
            view.swipeLayout.setOnRefreshListener {
                noticeDataList.value?.clear()
                refreshData(roomActivityArgs.roomUid!!)
            }

            isActive.observe(this@RoomActivity, Observer { active ->
                if(active){
                    startActivityForExtra(Intent(this@RoomActivity, WriteActivity::class.java), roomActivityArgs.roomUid!!)
                }
            })
        }
    }

    fun initRoomList(roomUid : String) {
        with(viewModel) {
            initObserveNoticeDataList(roomUid)
            addDisposable(initObserveUserList(roomUid))
            initUser(roomUid)
        }
    }

    fun startActivityForExtra(intent : Intent, extra : String) {
        intent.putExtra("roomUid", extra)
        startActivity(intent)
    }
}