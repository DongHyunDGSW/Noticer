package kr.hs.dgsw.donghyeon.noticer.view.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import kr.hs.dgsw.donghyeon.noticer.R
import kr.hs.dgsw.donghyeon.noticer.base.BaseActivity
import kr.hs.dgsw.donghyeon.noticer.databinding.ActivityCreateBinding
import kr.hs.dgsw.donghyeon.noticer.viewmodel.activites.CreateViewModel

class CreateActivity : BaseActivity<ActivityCreateBinding, CreateViewModel>() {
    override val layoutRes: Int
        get() = R.layout.activity_create
    override val viewModel: CreateViewModel
        get() = ViewModelProvider(this)[CreateViewModel::class.java]

    override fun onCreatedView(view: ActivityCreateBinding) {

    }
}