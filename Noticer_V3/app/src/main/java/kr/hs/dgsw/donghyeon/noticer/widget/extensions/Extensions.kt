package kr.hs.dgsw.donghyeon.noticer.widget.extensions

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import kr.hs.dgsw.donghyeon.noticer.base.constants.Constants
import kr.hs.dgsw.donghyeon.noticer.data.entity.NoticeEntity
import kr.hs.dgsw.donghyeon.noticer.data.entity.Row
import kr.hs.dgsw.donghyeon.noticer.view.adapters.listener.OnSchoolItemClickListener
import kr.hs.dgsw.donghyeon.noticer.viewmodel.fragments.SelectSchoolViewModel

fun convertToArray(hashMap: HashMap<String, NoticeEntity>) : ArrayList<NoticeEntity> {
    return ArrayList(hashMap.values)
}

// for recyclerView
fun RecyclerView.layoutManagerWithType(type : Int, context: Context) {
    when(type) {
        Constants.VERTICAL ->
           layoutManager = Constants.getVertical(context)

        Constants.HORIZONTAL ->
            layoutManager = Constants.getHorizontal(context)

        Constants.GRID ->
           layoutManager = Constants.getGrid(context)
    }
}