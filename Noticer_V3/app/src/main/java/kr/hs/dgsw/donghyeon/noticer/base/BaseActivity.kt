package kr.hs.dgsw.donghyeon.noticer.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import kr.hs.dgsw.donghyeon.noticer.BR

abstract class BaseActivity<VB : ViewDataBinding, VM : ViewModel> : AppCompatActivity() {

    lateinit var viewDataBinding : VB

    abstract val layoutRes : Int
    abstract val viewModel : VM
    abstract fun onCreatedView(view : VB)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        performViewDataBinding()
        onCreatedView(viewDataBinding)
    }

    private fun performViewDataBinding() {
        viewDataBinding = DataBindingUtil.setContentView(this, layoutRes)

        viewDataBinding.run {
            lifecycleOwner = this@BaseActivity
            setVariable(BR.viewModel, viewModel)
            executePendingBindings()
        }
    }
}