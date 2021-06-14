package kr.hs.dgsw.donghyeon.noticer.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import kr.hs.dgsw.donghyeon.noticer.view.activites.MainActivity

abstract class BaseFragment<VB : ViewDataBinding, VM : ViewModel> : Fragment() {

    lateinit var viewDataBinding : VB

    abstract val layoutRes : Int
    abstract val viewModel : VM
    abstract fun onCreatedView(view : VB)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        performViewDataBinding(inflater, container)

        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onCreatedView(viewDataBinding)
    }

    private fun performViewDataBinding(inflater: LayoutInflater, container: ViewGroup?) {

        viewDataBinding = DataBindingUtil.inflate(inflater, layoutRes, container, false)

        viewDataBinding.run {
            lifecycleOwner = requireActivity()
            setVariable(BR.viewModel, viewModel)
            executePendingBindings()
        }
    }
}