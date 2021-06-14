package kr.hs.dgsw.donghyeon.noticer.view.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainer
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import kr.hs.dgsw.donghyeon.noticer.R
import kr.hs.dgsw.donghyeon.noticer.base.BaseFragment
import kr.hs.dgsw.donghyeon.noticer.databinding.FragmentNoteBinding
import kr.hs.dgsw.donghyeon.noticer.view.activites.MainActivity
import kr.hs.dgsw.donghyeon.noticer.view.activites.RoomActivity
import kr.hs.dgsw.donghyeon.noticer.viewmodel.fragments.NoteViewModel
import kr.hs.dgsw.donghyeon.noticer.widget.extensions.startActivityForExtra

class NoteFragment : BaseFragment<FragmentNoteBinding, NoteViewModel>() {
    override val layoutRes: Int
        get() = R.layout.fragment_note
    override val viewModel: NoteViewModel
        get() = ViewModelProvider(requireActivity())[NoteViewModel::class.java]


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onCreatedView(viewDataBinding)

        with(viewModel) {
            actionRVClick.observe(requireActivity(), Observer { onClicked ->
                if(onClicked) {
                    Navigation.findNavController(requireActivity().findViewById<FragmentContainerView>(R.id.fragment)).navigate(NoteFragmentDirections.actionRoomActivity(roomDataInfo.value?.roomName!!, roomDataInfo.value?.roomUid!!))
                    actionRVClick.value = false
                }
            })
        }
    }

    override fun onCreatedView(view: FragmentNoteBinding) {

        Log.d("TAG", "? : ${requireActivity()}")
        with(viewModel) {
            view.swipeLayout.setOnRefreshListener {
                roomDataList.value?.clear()
                refreshData()
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("TAG_CONTEXT", "onAttached")
    }
}