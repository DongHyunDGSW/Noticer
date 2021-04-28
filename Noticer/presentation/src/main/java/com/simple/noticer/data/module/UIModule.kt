package com.simple.noticer.data.module

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.view.Window
import android.view.WindowManager
import com.simple.noticer.R

class UIModule {
    companion object{
        private fun setWindowFlag(bits: Int, on: Boolean, window: Window) {
            val win = window
            val winParams = win.attributes
            if (on) {
                winParams.flags = winParams.flags or bits
            } else {
                winParams.flags = winParams.flags and bits.inv()
            }
            win.attributes = winParams
        }

        fun setStatusBarTransparent(window: Window, activity : Activity, code : Int) {
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false, window)

            when(code) {
                CODE_MAIN -> {
                    activity.window.statusBarColor = Color.TRANSPARENT
                }

                CODE_LOAD -> {
                    activity.window.statusBarColor = activity.getColor(R.color.loadColor)
                }
            }
        }

        const val CODE_LOAD = 0
        const val CODE_MAIN = 1
    }
}