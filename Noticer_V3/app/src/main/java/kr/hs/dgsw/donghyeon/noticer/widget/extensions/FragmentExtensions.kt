package kr.hs.dgsw.donghyeon.noticer.widget.extensions

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import kr.hs.dgsw.donghyeon.noticer.R
import kr.hs.dgsw.donghyeon.noticer.base.BaseFragment
import kr.hs.dgsw.donghyeon.noticer.view.activites.MainActivity
import kr.hs.dgsw.donghyeon.noticer.view.activites.RoomActivity
import kr.hs.dgsw.donghyeon.noticer.view.fragments.NoteFragmentDirections

fun Fragment.setBackNavigationButton(bool : Boolean, title : String) {
    if(bool)
        requireActivity().actionBar?.title = title
}


fun Fragment.startActivityForExtra(extra : String?= null, secondExtra : String?= null) {

}