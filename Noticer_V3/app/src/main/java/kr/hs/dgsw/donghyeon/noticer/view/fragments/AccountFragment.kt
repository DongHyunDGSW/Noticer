package kr.hs.dgsw.donghyeon.noticer.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import kr.hs.dgsw.donghyeon.noticer.R
import kr.hs.dgsw.donghyeon.noticer.base.BaseFragment
import kr.hs.dgsw.donghyeon.noticer.databinding.FragmentAccountBinding
import kr.hs.dgsw.donghyeon.noticer.viewmodel.fragments.AccountViewModel

class AccountFragment : BaseFragment<FragmentAccountBinding, AccountViewModel>() {
    override val layoutRes: Int
        get() = R.layout.fragment_account
    override val viewModel: AccountViewModel
        get() = ViewModelProvider(requireActivity())[AccountViewModel::class.java]

    override fun onCreatedView(view: FragmentAccountBinding) {

    }
}