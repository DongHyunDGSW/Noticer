package com.simple.noticer.ui.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.simple.noticer.databinding.ActivitySelectBinding

class SelectActivity : AppCompatActivity() {

    private lateinit var selectBinding : ActivitySelectBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        selectBinding = ActivitySelectBinding.inflate(layoutInflater)

        setContentView(selectBinding.root)


    }
}