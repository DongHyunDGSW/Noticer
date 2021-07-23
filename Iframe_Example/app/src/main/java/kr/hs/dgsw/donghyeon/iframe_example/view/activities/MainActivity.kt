package kr.hs.dgsw.donghyeon.iframe_example.view.activities

import android.app.AlertDialog
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kr.hs.dgsw.donghyeon.data.NumberItemModel
import kr.hs.dgsw.donghyeon.iframe_example.viewmodel.activites.MainViewModel
import kr.hs.dgsw.donghyeon.iframe_example.R
import kr.hs.dgsw.donghyeon.iframe_example.base.BaseActivity
import kr.hs.dgsw.donghyeon.iframe_example.databinding.ActivityMainBinding
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val layoutRes: Int
        get() = R.layout.activity_main
    override val viewModel: MainViewModel
        get() = ViewModelProvider(this)[MainViewModel::class.java]

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onViewCreated(view: ActivityMainBinding) {
        with(viewModel) {
        }
    }
}