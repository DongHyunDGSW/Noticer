package kr.hs.dgsw.donghyeon.noticer.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import kr.hs.dgsw.donghyeon.noticer.data.entity.RoomEntity
import kr.hs.dgsw.donghyeon.noticer.data.entity.UserInfoEntity
import kr.hs.dgsw.donghyeon.noticer.databinding.ItemRoomBinding
import kr.hs.dgsw.donghyeon.noticer.view.adapters.listener.OnItemClickListener

class RoomAdapter(val onClicked : OnItemClickListener, private val roomDataList : ArrayList<RoomEntity> = arrayListOf()) : RecyclerView.Adapter<RoomViewHolder>() {

    fun setData(roomDataList: ArrayList<RoomEntity>) {
        this.roomDataList.run {
            clear()
            addAll(roomDataList)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder
         = RoomViewHolder(ItemRoomBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        holder.bind(roomDataList[position])
        holder.itemView.setOnClickListener {
            onClicked.onClicked(roomDataList[position])
        }
    }

    override fun getItemCount(): Int = roomDataList.size

}

class RoomViewHolder(val view : ItemRoomBinding) : RecyclerView.ViewHolder(view.root) {
    fun bind(data : RoomEntity) {
        view.textView.text = data.roomName
        view.textView2.text = data.roomFounder
    }
}