package kr.hs.dgsw.donghyeon.noticer.view.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kr.hs.dgsw.donghyeon.noticer.R
import kr.hs.dgsw.donghyeon.noticer.base.BaseActivity
import kr.hs.dgsw.donghyeon.noticer.databinding.ActivitySplashBinding
import kr.hs.dgsw.donghyeon.noticer.viewmodel.activites.SplashViewModel

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>(){
    override val layoutRes: Int
        get() = R.layout.activity_splash
    override val viewModel: SplashViewModel
        get() = ViewModelProvider(this)[SplashViewModel::class.java]

    override fun onCreatedView(view: ActivitySplashBinding) {
        with(viewModel) {
            onFinish()
            finishEvent.observe(this@SplashActivity, Observer { finish ->
                if(finish) {
                    finish()
                }
            })
        }
    }

}