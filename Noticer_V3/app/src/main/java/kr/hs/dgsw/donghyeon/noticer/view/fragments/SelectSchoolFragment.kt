package kr.hs.dgsw.donghyeon.noticer.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kr.hs.dgsw.donghyeon.noticer.R
import kr.hs.dgsw.donghyeon.noticer.base.BaseFragment
import kr.hs.dgsw.donghyeon.noticer.databinding.FragmentSelectSchoolBinding
import kr.hs.dgsw.donghyeon.noticer.viewmodel.fragments.SelectSchoolViewModel

class SelectSchoolFragment : BaseFragment<FragmentSelectSchoolBinding, SelectSchoolViewModel>() {
    override val layoutRes: Int
        get() = R.layout.fragment_select_school
    override val viewModel: SelectSchoolViewModel
        get() = ViewModelProvider(requireActivity())[SelectSchoolViewModel::class.java]

    override fun onCreatedView(view: FragmentSelectSchoolBinding) {

        val navArgs : SelectSchoolFragmentArgs by navArgs()

        with(viewModel) {
            actionToInfoInput.observe(requireActivity(), Observer { isCompleted ->
                if(isCompleted) {
                    findNavController().navigate(SelectSchoolFragmentDirections.actionToInputInfo(navArgs.email, navArgs.password))
                }
            })
        }
    }

}

