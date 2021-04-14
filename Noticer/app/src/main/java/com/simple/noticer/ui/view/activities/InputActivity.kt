package com.simple.noticer.ui.view.activities

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import com.simple.noticer.R
import com.simple.noticer.data.model.UserData
import com.simple.noticer.data.module.FirebaseAuthModule.Companion.currentUser
import com.simple.noticer.data.module.UIModule
import com.simple.noticer.databinding.ActivityInputBinding


class InputActivity : AppCompatActivity() {

    private lateinit var inputBinding : ActivityInputBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        UIModule.setStatusBarTransparent(window, this, UIModule.CODE_MAIN)
        inputBinding = ActivityInputBinding.inflate(layoutInflater)
        setContentView(inputBinding.root)

        updateWidget()
    }

    private fun updateWidget() {
        inputBinding.selectedSchool.text = "${intent.getStringExtra("schoolName")}네요 ! \n이제 학생 정보만 입력하면 끝나요 !"
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.menu_gender, android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        inputBinding.selectGender.adapter = adapter

        inputBinding.inputComplete.setOnClickListener {
            val user = currentUser()
            super.finish()
            overridePendingTransition(0, R.anim.slowly_gone)
            FirebaseDatabase.getInstance().reference.child("loginInfo").push().setValue(UserData(user?.uid, "none                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           ", inputBinding.nameEditText.text.toString(), intent.getStringExtra("schoolName") ,inputBinding.classEditText.text.toString()))
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(0, R.anim.slowly_gone)
    }
}