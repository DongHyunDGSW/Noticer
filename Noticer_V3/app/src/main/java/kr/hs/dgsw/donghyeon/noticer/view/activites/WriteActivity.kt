package kr.hs.dgsw.donghyeon.noticer.view.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kr.hs.dgsw.donghyeon.noticer.R
import kr.hs.dgsw.donghyeon.noticer.base.BaseActivity
import kr.hs.dgsw.donghyeon.noticer.databinding.ActivityWriteBinding
import kr.hs.dgsw.donghyeon.noticer.viewmodel.activites.WriteViewModel

class WriteActivity : BaseActivity<ActivityWriteBinding, WriteViewModel>() {
    override val layoutRes: Int
        get() = R.layout.activity_write
    override val viewModel: WriteViewModel
        get() = ViewModelProvider(this)[WriteViewModel::class.java]

    override fun onCreatedView(view: ActivityWriteBinding) {
        with(viewModel) {
            roomUid.value = intent.getStringExtra("roomUid")
            isCompleted.observe(this@WriteActivity, Observer {
                if(it) {
                    finish()
                }
            })
        }
    }

}