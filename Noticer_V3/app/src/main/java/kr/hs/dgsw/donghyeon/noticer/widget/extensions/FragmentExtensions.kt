package kr.hs.dgsw.donghyeon.noticer.widget.extensions

import androidx.fragment.app.Fragment

fun Fragment.setBackNavigationButton(bool : Boolean, title : String) {
    if(bool)
        requireActivity().actionBar?.title = title
}