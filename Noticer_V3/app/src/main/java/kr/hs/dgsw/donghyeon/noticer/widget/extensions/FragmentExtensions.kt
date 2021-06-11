package kr.hs.dgsw.donghyeon.noticer.widget.extensions

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import kr.hs.dgsw.donghyeon.noticer.view.activites.MainActivity
import kr.hs.dgsw.donghyeon.noticer.view.activites.RoomActivity

fun Fragment.setBackNavigationButton(bool : Boolean, title : String) {
    if(bool)
        requireActivity().actionBar?.title = title
}



inline fun<reified T : Any> Fragment.
        startActivityForExtra(isGotAnExtra : Boolean,
                              extraName : String?= null, extra : String?= null,
                              secondExtraName : String?= null, secondExtra : String?= null) {

    val intent = Intent(context, T::class.java)

    if(isGotAnExtra) {
        intent.putExtra(extraName, extra)
        intent.putExtra(secondExtraName, secondExtra)
    }

    startActivity(intent)
}