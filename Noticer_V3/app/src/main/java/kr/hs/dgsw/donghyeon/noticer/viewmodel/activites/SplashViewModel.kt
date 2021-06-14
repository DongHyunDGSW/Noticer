package kr.hs.dgsw.donghyeon.noticer.viewmodel.activites

import android.os.CountDownTimer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SplashViewModel : ViewModel() {
    val finishEvent = MutableLiveData<Boolean>()

    fun onFinish() {
        val countTimer = object : CountDownTimer(3000, 1000){
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                finishEvent.value = true
            }
        }

        countTimer.start()
    }
}