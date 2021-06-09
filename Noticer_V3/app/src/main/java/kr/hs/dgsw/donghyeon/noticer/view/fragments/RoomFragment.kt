package kr.hs.dgsw.donghyeon.noticer.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import kr.hs.dgsw.donghyeon.noticer.R
import kr.hs.dgsw.donghyeon.noticer.base.BaseFragment
import kr.hs.dgsw.donghyeon.noticer.databinding.FragmentRoomBinding
import kr.hs.dgsw.donghyeon.noticer.viewmodel.fragments.RoomViewModel
import kr.hs.dgsw.donghyeon.noticer.widget.extensions.setBackNavigationButton
import kr.hs.dgsw.donghyeon.noticer.widget.extensions.setSupportCollapsingToolbar

class RoomFragment : BaseFragment<FragmentRoomBinding, RoomViewModel>() {

    val roomNavArgs : RoomFragmentArgs by navArgs()

    override val layoutRes: Int
        get() = R.layout.fragment_room
    override val viewModel: RoomViewModel
        get() = ViewModelProvider(requireActivity())[RoomViewModel::class.java]

    override fun onCreatedView(view: FragmentRoomBinding) {
        with(viewModel) {
            view.swipeLayout.setOnRefreshListener {
                noticeDataList.value?.clear()
                refreshData(roomNavArgs.roomUid!!)
            }

            (requireActivity() as AppCompatActivity).setSupportCollapsingToolbar(view.toolbar, view.collapsingToolbar, roomNavArgs.roomTitle!!)
            initObserveNoticeDataList(roomNavArgs.roomUid!!)
        }
    }
}