package kr.hs.dgsw.donghyeon.noticer.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kr.hs.dgsw.donghyeon.noticer.R
import kr.hs.dgsw.donghyeon.noticer.base.BaseFragment
import kr.hs.dgsw.donghyeon.noticer.databinding.FragmentNoteBinding
import kr.hs.dgsw.donghyeon.noticer.view.activites.RoomActivity
import kr.hs.dgsw.donghyeon.noticer.viewmodel.fragments.NoteViewModel
import kr.hs.dgsw.donghyeon.noticer.widget.extensions.startActivityForExtra

class NoteFragment : BaseFragment<FragmentNoteBinding, NoteViewModel>() {
    override val layoutRes: Int
        get() = R.layout.fragment_note
    override val viewModel: NoteViewModel
        get() = ViewModelProvider(requireActivity())[NoteViewModel::class.java]

    override fun onCreatedView(view: FragmentNoteBinding) {
        with(viewModel) {
            view.swipeLayout.setOnRefreshListener {
                roomDataList.value?.clear()
                refreshData()
            }

            actionRVClick.observe(requireActivity(), Observer { onClicked ->
                if(onClicked) {
                    startActivityForExtra<RoomActivity>(
                        true,
                        "roomTitle",
                        roomDataInfo.value?.roomName!!,
                        "roomUid",
                        roomDataInfo.value?.roomUid!!
                    )
                    actionRVClick.value = false
                }
            })
        }
    }
}