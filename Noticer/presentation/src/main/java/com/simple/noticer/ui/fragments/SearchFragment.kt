package com.simple.noticer.ui.fragments

import RoomAdapter
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseUser
import com.simple.noticer.R
import com.simple.data.model.RoomData
import com.simple.noticer.data.module.FirebaseDataBaseModule
import com.simple.noticer.databinding.FragmentSearchBinding
import com.simple.noticer.viewmodel.MainViewModel
import kotlinx.coroutines.*


class SearchFragment: Fragment() {

    private lateinit var searchBinding: FragmentSearchBinding
    private lateinit var roomAdapter: RoomAdapter
    private val viewModel: MainViewModel by lazy { MainViewModel(requireActivity().application) }
    private suspend fun firebaseDataBaseModule() {
        FirebaseDataBaseModule.jobGetRoom.await()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        searchBinding = FragmentSearchBinding.inflate(layoutInflater)

        return searchBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        GlobalScope.launch {
            FirebaseDataBaseModule.getRoomList(viewModel)
        }

        initModel()
    }

    private fun initLayout() {
        searchBinding.toolbar.setBackgroundColor(resources.getColor(R.color.white))
        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                searchBinding.toolbar.title = "Noticer!"
                searchBinding.toolbar.setTitleTextColor(resources.getColor(R.color.black))
            }
        }

        searchBinding.swipeLayout.setOnRefreshListener {
            searchBinding.swipeLayout.isRefreshing = false
            GlobalScope.launch {
                firebaseDataBaseModule()
            }
        }
    }

    private fun initRecyclerView() {

        roomAdapter = RoomAdapter(ArrayList<RoomData>())

        searchBinding.rvCardList.layoutManager = GridLayoutManager(requireContext(), 3)
        searchBinding.rvCardList.adapter = roomAdapter

        viewModel.roomDataLiveList.observe(requireActivity(), Observer {
            roomAdapter.setData(it)
        })
    }


    private fun initModel() {

        searchBinding.loadingView.startAnimation(AnimationUtils.loadAnimation(requireContext(), R.anim.slowly_visible))
        searchBinding.loadingView.visibility = View.VISIBLE
        GlobalScope.launch {
            initLayout()

            firebaseDataBaseModule()

            withContext(Dispatchers.Main) {
                initRecyclerView()
            }
        }
    }

    override fun onResume() {
        super.onResume()

        GlobalScope.launch {
            firebaseDataBaseModule()
        }

        searchBinding.loadingView.startAnimation(AnimationUtils.loadAnimation(requireContext(), R.anim.slowly_gone))
        searchBinding.loadingView.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    fun updateUI(user: FirebaseUser?) {
        Snackbar.make(requireView(), "환영합니다 ! ${user?.displayName}님 !", Snackbar.LENGTH_LONG).show()
    }
}