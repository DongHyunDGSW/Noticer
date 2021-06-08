package kr.hs.dgsw.donghyeon.noticer.view.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kr.hs.dgsw.donghyeon.noticer.R
import kr.hs.dgsw.donghyeon.noticer.base.BaseFragment
import kr.hs.dgsw.donghyeon.noticer.databinding.FragmentHomeBinding
import kr.hs.dgsw.donghyeon.noticer.view.activites.LoginActivity
import kr.hs.dgsw.donghyeon.noticer.viewmodel.fragments.HomeViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override val layoutRes: Int
        get() = R.layout.fragment_home
    override val viewModel: HomeViewModel
        get() = ViewModelProvider(requireActivity())[HomeViewModel::class.java]

    override fun onCreatedView(view: FragmentHomeBinding) {
        requireActivity().startActivity(Intent(requireActivity(), LoginActivity::class.java))

        with(viewModel) {
            view.swipeLayout.setOnRefreshListener {
                roomDataList.value?.clear()
                refreshData()
            }
        }
    }
}