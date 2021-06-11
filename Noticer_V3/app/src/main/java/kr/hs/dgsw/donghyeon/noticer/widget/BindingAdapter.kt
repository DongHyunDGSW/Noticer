package kr.hs.dgsw.donghyeon.noticer.widget

import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kr.hs.dgsw.donghyeon.noticer.R
import kr.hs.dgsw.donghyeon.noticer.base.constants.Constants
import kr.hs.dgsw.donghyeon.noticer.widget.extensions.layoutManagerWithType

class BindingAdapter {
    companion object {
        @JvmStatic
        @BindingAdapter(value = ["bind:bindAdapter", "bind:setType"], requireAll = true)
        fun bindAdapter(recyclerView : RecyclerView?, adapter : RecyclerView.Adapter<*>?, type : Int) {
            recyclerView?.layoutManagerWithType(type, recyclerView.context!!)
            recyclerView?.adapter = adapter

            Log.d("TAG", "your lm is ${recyclerView?.layoutManager}")
        }

        @JvmStatic
        @BindingAdapter("bind:isCompleted")
        fun bindCheck(refreshLayout: SwipeRefreshLayout?, taskComplete : Boolean) {
            refreshLayout?.isRefreshing = !taskComplete
        }

        @JvmStatic
        @BindingAdapter("bind:bindArrayGenderAdapter")
        fun bindGender(selectGender : Spinner?, adapter : ArrayAdapter<*>?) {
            adapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            selectGender?.adapter = adapter
        }
    }
}