package kr.hs.dgsw.donghyeon.noticer.widget

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class BindingAdapter {
    companion object {
        @BindingAdapter("bind:bindAdapter")
        @JvmStatic
        fun bindAdapter(recyclerView : RecyclerView?, adapter : RecyclerView.Adapter<*>) {
            recyclerView?.layoutManager = GridLayoutManager(recyclerView?.context, 3)
            recyclerView?.adapter = adapter
        }

        @BindingAdapter("bind:isCompleted")
        @JvmStatic
        fun bindCheck(refreshLayout: SwipeRefreshLayout?, taskComplete : Boolean) {
            if(taskComplete) refreshLayout?.isRefreshing = false
        }
    }
}