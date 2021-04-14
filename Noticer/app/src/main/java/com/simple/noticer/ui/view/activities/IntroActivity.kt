package com.simple.noticer.ui.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.telecom.Call
import com.simple.noticer.R
import com.simple.noticer.data.module.UIModule
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        UIModule.setStatusBarTransparent(window, this, UIModule.CODE_MAIN)
        setContentView(R.layout.activity_intro)

        overridePendingTransition(R.anim.slowly_visible, 0)

        Handler().postDelayed(Runnable {
            finish()
            startActivity(Intent(this, LoginActivity::class.java))
            overridePendingTransition(R.anim.slowly_visible, 0)
        }, 2500)
    }

    override fun onPause() {
        super.onPause()
        if(isFinishing) {
            overridePendingTransition(0, R.anim.slowly_gone)
        }
    }

    override fun onBackPressed() {
        return
    }
}