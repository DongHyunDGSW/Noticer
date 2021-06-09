package kr.hs.dgsw.donghyeon.noticer.view.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import kr.hs.dgsw.donghyeon.noticer.R
import kr.hs.dgsw.donghyeon.noticer.base.BaseActivity
import kr.hs.dgsw.donghyeon.noticer.databinding.ActivityMainBinding
import kr.hs.dgsw.donghyeon.noticer.viewmodel.activites.MainViewModel
import kr.hs.dgsw.donghyeon.noticer.widget.extensions.setSupportCollapsingToolbar

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override val layoutRes: Int
        get() = R.layout.activity_main
    override val viewModel: MainViewModel
        get() = ViewModelProvider(this)[MainViewModel::class.java]

    override fun onCreatedView(view: ActivityMainBinding) {
    }
}