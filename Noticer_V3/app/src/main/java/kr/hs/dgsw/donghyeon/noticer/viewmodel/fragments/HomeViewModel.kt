package kr.hs.dgsw.donghyeon.noticer.viewmodel.fragments

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.androidhuman.rxfirebase2.database.ChildAddEvent
import com.androidhuman.rxfirebase2.database.childEvents
import io.reactivex.disposables.Disposable
import kr.hs.dgsw.donghyeon.noticer.base.BaseViewModel
import kr.hs.dgsw.donghyeon.noticer.data.entity.NoticeEntity
import kr.hs.dgsw.donghyeon.noticer.data.entity.RoomEntity
import kr.hs.dgsw.donghyeon.noticer.data.entity.UserInfoEntity
import kr.hs.dgsw.donghyeon.noticer.view.adapters.RoomAdapter
import kr.hs.dgsw.donghyeon.noticer.view.adapters.listener.OnItemClickListener
import kr.hs.dgsw.donghyeon.noticer.widget.extensions.convertToArray

class HomeViewModel : BaseViewModel() {
}