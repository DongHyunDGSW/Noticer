package kr.hs.dgsw.donghyeon.noticer.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kr.hs.dgsw.donghyeon.noticer.R
import kr.hs.dgsw.donghyeon.noticer.base.BaseFragment
import kr.hs.dgsw.donghyeon.noticer.data.entity.UserInfoEntity
import kr.hs.dgsw.donghyeon.noticer.databinding.FragmentInputInfoBinding
import kr.hs.dgsw.donghyeon.noticer.viewmodel.fragments.InputInfoViewModel

class InputInfoFragment : BaseFragment<FragmentInputInfoBinding, InputInfoViewModel>() {
    override val layoutRes: Int
        get() = R.layout.fragment_input_info
    override val viewModel: InputInfoViewModel
        get() = ViewModelProvider(requireActivity())[InputInfoViewModel::class.java]

    override fun onCreatedView(view: FragmentInputInfoBinding) {
        val info : InputInfoFragmentArgs by navArgs()
        with(viewModel) {
            email.value = info.email
            password.value = info.password
            schoolName.value = info.schoolName

            selectedData.value = "${info.schoolName}네요 ! \n이제 학생 정보만 입력하면 끝나요 !"

            isRegisterCompleted.observe(requireActivity(), Observer { isCompleted ->
                if(isCompleted) {
                }
            })

            isLoginCompleted.observe(requireActivity(), Observer { isLoginCompleted ->
                if(isLoginCompleted) {
                    Log.d("TAG", "hi?")
                    requireActivity().finish()
                }
            })
        }
    }

}