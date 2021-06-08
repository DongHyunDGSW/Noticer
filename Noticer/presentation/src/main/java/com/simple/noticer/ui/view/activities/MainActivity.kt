package com.simple.noticer.ui.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.simple.noticer.R
import com.simple.noticer.data.module.UIModule
import com.simple.noticer.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        UIModule.setStatusBarTransparent(window, this, UIModule.CODE_MAIN)
        setContentView(mainBinding.root)

        startActivity(Intent(this, IntroActivity::class.java))

        mainBinding.mainBottomNavi.setupWithNavController(findNavController(R.id.fragmentMain))
    }

    override fun onBackPressed() {
        val alertDialog = AlertDialog.Builder(this)
            .setMessage("Noticer를 종료하시겠습니까?")
            .setPositiveButton("네") { di , i -> finish() }
            .setNegativeButton("아니오") {di, i ->}
            .setCancelable(false)
            .create()

        alertDialog.show()
    }
}