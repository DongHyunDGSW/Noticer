package com.simple.noticer.ui.view.activities

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.simple.noticer.R
import com.simple.noticer.data.model.UserData
import com.simple.noticer.data.module.FirebaseAuthModule
import com.simple.noticer.data.module.UIModule
import com.simple.noticer.databinding.ActivityLoginBinding
import com.simple.noticer.ui.fragments.SearchFragment

class LoginActivity : AppCompatActivity() {

    private lateinit var loginBinding: ActivityLoginBinding
    private lateinit var user : FirebaseUser
    private val sharedPreferences : SharedPreferences by lazy{ getSharedPreferences("isLogined", MODE_PRIVATE) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        UIModule.setStatusBarTransparent(window, this, UIModule.CODE_MAIN)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)

        checkLogin()

//        Glide.with(applicationContext).load(R.raw.background).centerCrop().into(loginBinding.backgroundImage)

        loginBinding.loginButtonDefault.setOnClickListener {
            FirebaseAuthModule.loginWithEmail(loginBinding.loginEmail.text.toString(), loginBinding.loginPassword.text.toString(), this)
        }

        loginBinding.registerTextView.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            overridePendingTransition(R.anim.slowly_visible, 0)
        }

        loginBinding.loginButtonFacebook.setOnClickListener {
            val result = FirebaseAuthModule.facebookLogin(this)
            Log.d("TAG_RESULT", "${result}")

            if(result != null)
                user = result
        }
    }

    fun loginTaskDone(user : FirebaseUser?) {
        finish()
        Log.d("TAG", "${user?.email}")
    }

    override fun onBackPressed() {
        return
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(0, R.anim.slowly_gone)
    }

    fun onSaveLogin(user : FirebaseUser) {
        val editor = sharedPreferences.edit()

        editor.putBoolean("isLogined", true)
        editor.putString("userUid", user.uid)

        editor.apply()
    }

    private fun checkLogin() {
        if(sharedPreferences.getBoolean("isLogined", false)) {
            finish()
            overridePendingTransition(0, R.anim.slowly_gone)
        }
    }
}