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
import kr.hs.dgsw.donghyeon.noticer.view.adapters.NoticeAdapter
import kr.hs.dgsw.donghyeon.noticer.view.adapters.RoomAdapter
import kr.hs.dgsw.donghyeon.noticer.view.adapters.listener.OnItemClickListener
import kr.hs.dgsw.donghyeon.noticer.view.adapters.listener.OnNoticeClickListener

class RoomViewModel : BaseViewModel() {

    val noticeDataList = MutableLiveData<ArrayList<NoticeEntity>>()
    var hasCompleted = MutableLiveData<Boolean>()

    val noticeItemAdapter = NoticeAdapter(object : OnNoticeClickListener {
        override fun onClicked(data: NoticeEntity) {

        }
    })

    init {
        initializeData()
    }

    private fun initializeData() {
        noticeDataList.value = arrayListOf()
    }

    fun refreshData(roomUid : String) {
        addDisposable(initObserveNoticeDataList(roomUid))
    }

    fun initObserveNoticeDataList(roomUid : String): Disposable {
        return ref.child("roomData").child(roomUid).child("noticeList").childEvents()
            .ofType(ChildAddEvent::class.java)
            .doOnSubscribe {
                hasCompleted.value = true
                Log.d("TAG", "Completed")
            }.subscribe({ response ->
                val data = response.dataSnapshot().getValue(NoticeEntity::class.java)

                val dataNotice = NoticeEntity(
                    data!!.title,
                    data.content,
                    data.writer,
                    data.imagePath,
                    data.comment
                )

                noticeDataList.value?.add(dataNotice)
                Log.d("TAG", "${noticeDataList.value!!}")
                noticeItemAdapter.setData(noticeDataList.value!!)
            }) { Log.d("TAG", "${it.message}") }
    }
}