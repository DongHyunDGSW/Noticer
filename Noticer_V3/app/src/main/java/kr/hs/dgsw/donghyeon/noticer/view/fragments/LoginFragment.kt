package kr.hs.dgsw.donghyeon.noticer.view.fragments

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
import kr.hs.dgsw.donghyeon.noticer.databinding.FragmentLoginBinding
import kr.hs.dgsw.donghyeon.noticer.viewmodel.fragments.LoginViewModel

class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {
    override val layoutRes: Int
        get() = R.layout.fragment_login
    override val viewModel: LoginViewModel
        get() = ViewModelProvider(requireActivity())[LoginViewModel::class.java]

    override fun onCreatedView(view: FragmentLoginBinding) {
        with(viewModel) {
            isLoginCompleted.observe(requireActivity(), Observer { hasCompleted ->
                if(hasCompleted) {
                    requireActivity().finish()
                }
            })

            registerClickEvent.observe(requireActivity(), Observer { clickEvent ->
                if(clickEvent) {
                    findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
                    registerClickEvent.value = false
                }
            })
        }
    }
}