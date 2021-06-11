package kr.hs.dgsw.donghyeon.noticer.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import io.reactivex.internal.operators.observable.ObservableNever
import kr.hs.dgsw.donghyeon.noticer.R
import kr.hs.dgsw.donghyeon.noticer.base.BaseFragment
import kr.hs.dgsw.donghyeon.noticer.base.constants.Constants
import kr.hs.dgsw.donghyeon.noticer.databinding.FragmentRegisterBinding
import kr.hs.dgsw.donghyeon.noticer.viewmodel.fragments.RegisterViewModel

class RegisterFragment : BaseFragment<FragmentRegisterBinding, RegisterViewModel>() {
    override val layoutRes: Int
        get() = R.layout.fragment_register
    override val viewModel: RegisterViewModel
        get() = ViewModelProvider(requireActivity())[RegisterViewModel::class.java]

    override fun onCreatedView(view: FragmentRegisterBinding) {
        with(viewModel) {
            actionToSelectSchool.observe(requireActivity(), Observer { isCompleted ->
                if(isCompleted) {
                    findNavController().navigate(RegisterFragmentDirections.actionToSelectSchool(email.value, password.value))
                }
            })
        }
    }

}