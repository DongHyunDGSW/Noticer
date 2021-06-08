package kr.hs.dgsw.donghyeon.noticer.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import kr.hs.dgsw.donghyeon.noticer.R
import kr.hs.dgsw.donghyeon.noticer.base.BaseFragment
import kr.hs.dgsw.donghyeon.noticer.databinding.FragmentInputInfoBinding
import kr.hs.dgsw.donghyeon.noticer.viewmodel.fragments.InputInfoViewModel

class InputInfoFragment : BaseFragment<FragmentInputInfoBinding, InputInfoViewModel>() {
    override val layoutRes: Int
        get() = R.layout.fragment_input_info
    override val viewModel: InputInfoViewModel
        get() = ViewModelProvider(requireActivity())[InputInfoViewModel::class.java]

    override fun onCreatedView(view: FragmentInputInfoBinding) {

    }

}