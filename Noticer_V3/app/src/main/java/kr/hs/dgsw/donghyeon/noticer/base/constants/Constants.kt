package kr.hs.dgsw.donghyeon.noticer.base.constants

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

class Constants {
    companion object {
        fun getVertical(context : Context) : LinearLayoutManager
            = GridLayoutManager(context, 1)

        fun getHorizontal(context : Context) : LinearLayoutManager
            = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        fun getGrid(context : Context) : GridLayoutManager
            = GridLayoutManager(context, 3)

        const val VERTICAL = 3
        const val HORIZONTAL = 4
        const val GRID = 5

        const val ID_CHECK = 0
        const val PASSWORD_CHECK = 1
        const val PASSWORD_DUPLICATE_CHECK = 2

        const val BASE_URL = "https://open.neis.go.kr/hub/"
        const val KEY = "503bcc6acfd4461f8fdf117c90319b51"
    }
}