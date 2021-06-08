package kr.hs.dgsw.donghyeon.noticer.view.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import kr.hs.dgsw.donghyeon.noticer.R
import kr.hs.dgsw.donghyeon.noticer.base.BaseActivity
import kr.hs.dgsw.donghyeon.noticer.databinding.ActivityLoginBinding
import kr.hs.dgsw.donghyeon.noticer.viewmodel.activites.SignViewModel

class LoginActivity : BaseActivity<ActivityLoginBinding, SignViewModel>() {
    override val layoutRes: Int
        get() = R.layout.activity_login
    override val viewModel: SignViewModel
        get() = ViewModelProvider(this)[SignViewModel::class.java]

    override fun onCreatedView(view: ActivityLoginBinding) {

    }

    override fun onBackPressed() {
        return
    }
}