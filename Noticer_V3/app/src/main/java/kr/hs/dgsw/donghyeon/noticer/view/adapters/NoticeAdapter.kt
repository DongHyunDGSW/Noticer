package kr.hs.dgsw.donghyeon.noticer.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import kr.hs.dgsw.donghyeon.noticer.data.entity.NoticeEntity
import kr.hs.dgsw.donghyeon.noticer.data.entity.RoomEntity
import kr.hs.dgsw.donghyeon.noticer.databinding.ItemNoticeBinding
import kr.hs.dgsw.donghyeon.noticer.view.adapters.listener.OnNoticeClickListener

class NoticeAdapter(val onClicked : OnNoticeClickListener, private val noticeDataList : ArrayList<NoticeEntity> = arrayListOf()) : RecyclerView.Adapter<NoticeViewHolder>() {

    fun setData(noticeDataList: ArrayList<NoticeEntity>) {
        this.noticeDataList.run {
            clear()
            addAll(noticeDataList)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeViewHolder
         = NoticeViewHolder(ItemNoticeBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: NoticeViewHolder, position: Int) {
        holder.bind(noticeDataList[position])
        holder.itemView.setOnClickListener {
            onClicked.onClicked(noticeDataList[position])
        }
    }

    override fun getItemCount(): Int = noticeDataList.size
}

class NoticeViewHolder(val view : ItemNoticeBinding) : RecyclerView.ViewHolder(view.root) {
    fun bind(data : NoticeEntity) {
        view.noticeTitle.text = data.title
        view.noticeWriter.text = data.writer
    }
}