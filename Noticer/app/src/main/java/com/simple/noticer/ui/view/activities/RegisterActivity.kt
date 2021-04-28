package com.simple.noticer.ui.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseUser
import com.simple.noticer.R
import com.simple.noticer.data.module.FirebaseAuthModule
import com.simple.noticer.data.module.UIModule
import com.simple.noticer.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var registerBinding : ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        UIModule.setStatusBarTransparent(window, this, UIModule.CODE_MAIN)
        registerBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(registerBinding.root)

        updateWidget()
    }

    private fun updateWidget() {
        registerBinding.registerButtonDefault.setOnClickListener {
            if(registerBinding.checkBoxRequired.isChecked) {
                FirebaseAuthModule.registerWithEmail(registerBinding.registerEmail.text.toString(), registerBinding.registerPassword.text.toString(), this)
            }
        }

        registerBinding.loginTextView.setOnClickListener {
            finish()
        }
    }

    fun registerTaskDone(user : FirebaseUser?) {
        finish()
        startActivity(Intent(this, SelectSchoolActivity::class.java))
        overridePendingTransition(R.anim.slowly_visible, 0)
        Log.d("TAG", "${user?.email}")
    }

    override fun onBackPressed() {
        return
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(0, R.anim.slowly_gone)
    }
}