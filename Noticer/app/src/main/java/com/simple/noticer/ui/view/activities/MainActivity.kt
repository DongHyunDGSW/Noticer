package com.simple.noticer.ui.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.simple.noticer.R
import com.simple.noticer.data.module.UIModule
import com.simple.noticer.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var mainBinding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        UIModule.setStatusBarTransparent(window, this, UIModule.CODE_MAIN)
        setContentView(mainBinding.root)

        startActivity(Intent(this, IntroActivity::class.java))
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.page_home -> {
               // Navigation.findNavController(this@MainActivity, R.id.fragmentMain).navigate(R.id.action_searchFragment_to_mainFragment)
            }
            R.id.page_search -> {
              //  Navigation.findNavController(this@MainActivity, R.id.fragmentMain).navigate(R.id.action_searchFragment_to_mainFragment)
            }

        }
        return false
    }
}