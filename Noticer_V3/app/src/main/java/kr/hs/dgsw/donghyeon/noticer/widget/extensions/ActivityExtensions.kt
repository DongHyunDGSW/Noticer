package kr.hs.dgsw.donghyeon.noticer.widget.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.appbar.CollapsingToolbarLayout


fun AppCompatActivity.setSupportCollapsingToolbar(toolbar : Toolbar, collapsingToolbarLayout: CollapsingToolbarLayout, title : String) {
    setSupportActionBar(toolbar)
    collapsingToolbarLayout.title = title
}