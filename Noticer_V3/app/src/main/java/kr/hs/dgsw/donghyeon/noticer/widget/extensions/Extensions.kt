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

//On Register Check
fun onCheckEditTextError(editText : TextInputEditText, editText2: TextInputEditText , type : Int) {
    when(type) {
        Constants.ID_CHECK -> {
            if(editText.text.isNullOrEmpty())
                editText.error = "아이디를 확인해주세요."
        }

        Constants.PASSWORD_CHECK -> {
            if(editText.text.isNullOrEmpty())
                editText.error = "비밀번호를 확인해주세요."
        }

        Constants.PASSWORD_DUPLICATE_CHECK -> {
            if(editText.text.toString()
                != editText2.text.toString())
                editText.error = "비밀번호가 다릅니다."
        }
    }
}

fun showDialog(context: Context, data : Row, viewModel : SelectSchoolViewModel) {
    AlertDialog.Builder(context)
        .setTitle("재차 확인")
        .setMessage("${data.SCHUL_NM}가 맞나요?")
        .setPositiveButton("네") { a, b ->
            viewModel.actionToInfoInput.value = true
        }.setNegativeButton("아니오") { a, b -> }
        .create()
        .show()
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