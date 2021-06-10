package kr.hs.dgsw.donghyeon.noticer.view.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kr.hs.dgsw.donghyeon.noticer.R
import kr.hs.dgsw.donghyeon.noticer.base.BaseFragment
import kr.hs.dgsw.donghyeon.noticer.databinding.FragmentHomeBinding
import kr.hs.dgsw.donghyeon.noticer.view.activites.LoginActivity
import kr.hs.dgsw.donghyeon.noticer.view.activites.RoomActivity
import kr.hs.dgsw.donghyeon.noticer.viewmodel.fragments.HomeViewModel
import kr.hs.dgsw.donghyeon.noticer.widget.extensions.startActivityForExtra

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override val layoutRes: Int
        get() = R.layout.fragment_home
    override val viewModel: HomeViewModel
        get() = ViewModelProvider(requireActivity())[HomeViewModel::class.java]

    override fun onCreatedView(view: FragmentHomeBinding) {
        with(viewModel) {
            view.swipeLayout.setOnRefreshListener {
                roomDataList.value?.clear()
                refreshData()
            }

            actionRVClick.observe(requireActivity(), Observer { onClicked ->
                if(onClicked) {
                    startActivityForExtra<RoomActivity>(
                        true,
                        "roomTitle",
                        roomDataInfo.value?.roomName!!,
                        "roomUid",
                        roomDataInfo.value?.roomUid!!
                    )
                    actionRVClick.value = false
                }
            })
        }
    }
}