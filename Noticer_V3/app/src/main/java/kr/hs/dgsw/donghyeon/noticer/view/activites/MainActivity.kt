package kr.hs.dgsw.donghyeon.noticer.view.activites

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.firebase.auth.FirebaseAuth
import kr.hs.dgsw.donghyeon.noticer.R
import kr.hs.dgsw.donghyeon.noticer.base.BaseActivity
import kr.hs.dgsw.donghyeon.noticer.databinding.ActivityMainBinding
import kr.hs.dgsw.donghyeon.noticer.viewmodel.activites.MainViewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override val layoutRes: Int
        get() = R.layout.activity_main
    override val viewModel: MainViewModel
        get() = ViewModelProvider(this)[MainViewModel::class.java]

    override fun onCreatedView(view: ActivityMainBinding) {
        startActivity(Intent(this, SplashActivity::class.java))
        view.bottomNavigationView.setupWithNavController(supportFragmentManager.findFragmentById(R.id.fragment)!!.findNavController())
        checkCurrentUser()
    }

    private fun checkCurrentUser() {
        if(FirebaseAuth.getInstance().currentUser == null)
            startActivity(Intent(this, LoginActivity::class.java))
    }
}