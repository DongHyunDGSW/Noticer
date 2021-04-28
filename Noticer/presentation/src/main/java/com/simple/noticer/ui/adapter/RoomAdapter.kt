
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.simple.data.model.RoomData
import com.simple.noticer.databinding.ItemRoomBinding


class RoomAdapter(var roomList: ArrayList<RoomData>) : RecyclerView.Adapter<RoomViewHolder>() {

    private lateinit var itemRoomBinding : ItemRoomBinding

    fun setData(roomList: ArrayList<RoomData>) {
        this.roomList = roomList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        itemRoomBinding = ItemRoomBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return RoomViewHolder(itemRoomBinding)
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        holder.bind(roomList[position])
    }

    override fun getItemCount(): Int {
        return roomList.size
    }
}

class RoomViewHolder(val itemRoomBinding: ItemRoomBinding) : RecyclerView.ViewHolder(itemRoomBinding.root) {
    fun bind(itemRoom : RoomData) {
        itemRoomBinding.roomTitle.text = itemRoom.roomName
    }
}