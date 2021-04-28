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

    private lateinit var searchBinding : FragmentSearchBinding
    private lateinit var roomAdapter : RoomAdapter
    private lateinit var viewModel : MainViewModel
    val jobCompleted = FirebaseDataBaseModule.jobGetRoom

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        searchBinding = FragmentSearchBinding.inflate(layoutInflater)

        return searchBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = MainViewModel(requireActivity().application)
        super.onViewCreated(view, savedInstanceState)

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
                getJobComplete(REFRESH)
            }
        }
    }

    private fun initRecyclerView() {

        roomAdapter = RoomAdapter(ArrayList<RoomData>())

        searchBinding.rvCardList.layoutManager = GridLayoutManager(requireContext(), 3)
        searchBinding.rvCardList.adapter = roomAdapter

        viewModel.roomDataLiveList.observe(requireActivity(), Observer {
            roomAdapter.setData(it)
            Log.d("Observed", "OBSERVED! DATA : ${it.toString()}")
        })
    }

    private suspend fun getJobComplete(CODE : Int? = INITIALIZE) {
        val jobCompletedList = jobCompleted.await()

        if(jobCompleted.isCompleted) {
            withContext(Dispatchers.Main) {

                if(CODE == INITIALIZE)
                    viewModel.roomDataLiveList.value = jobCompletedList

            }
        }
        Log.d("TAG_", "inCage : ${jobCompleted.isActive} , ${jobCompleted.isCompleted} , ${jobCompleted.isCancelled}")
    }

    private fun initModel() {

        searchBinding.loadingView.startAnimation(AnimationUtils.loadAnimation(requireContext(), R.anim.slowly_visible))
        searchBinding.loadingView.visibility = View.VISIBLE
        GlobalScope.launch {
            initLayout()

            getJobComplete()

            withContext(Dispatchers.Main) {
                initRecyclerView()
            }
        }
    }

    override fun onResume() {
        super.onResume()

        GlobalScope.launch {
            getJobComplete(REFRESH)
        }

        searchBinding.loadingView.startAnimation(AnimationUtils.loadAnimation(requireContext(), R.anim.slowly_gone))
        searchBinding.loadingView.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()

        jobCompleted.cancel()
    }

    fun updateUI(user : FirebaseUser?) {
        Snackbar.make(requireView(), "환영합니다 ! ${user?.displayName}님 !", Snackbar.LENGTH_LONG).show()
    }
    companion object {
        const val INITIALIZE = 0
        const val REFRESH = 1
    }
}