package com.simple.noticer.ui.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.simple.noticer.R
import com.simple.noticer.databinding.ActivityCreateRoomBinding

class CreateRoomActivity : AppCompatActivity() {

    private lateinit var createRoomBinding : ActivityCreateRoomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        createRoomBinding = ActivityCreateRoomBinding.inflate(layoutInflater)
        setContentView(createRoomBinding.root)


    }

    override fun finish() {
        super.finish()
        overridePendingTransition(0, R.anim.slowly_gone)
    }
}