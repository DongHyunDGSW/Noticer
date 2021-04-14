package com.simple.noticer.ui.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.simple.noticer.R
import com.simple.noticer.data.module.UIModule
import com.simple.noticer.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var registerBinding : ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        UIModule.setStatusBarTransparent(window, this, UIModule.CODE_MAIN)
        registerBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(registerBinding.root)


    }

    override fun finish() {
        super.finish()
        overridePendingTransition(0, R.anim.slowly_gone)
    }
}