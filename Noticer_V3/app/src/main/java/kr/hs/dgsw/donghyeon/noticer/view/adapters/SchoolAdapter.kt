package kr.hs.dgsw.donghyeon.noticer.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.hs.dgsw.donghyeon.noticer.data.entity.RoomEntity
import kr.hs.dgsw.donghyeon.noticer.data.entity.Row
import kr.hs.dgsw.donghyeon.noticer.databinding.ItemSchoolBinding
import kr.hs.dgsw.donghyeon.noticer.view.adapters.listener.OnSchoolItemClickListener

class SchoolAdapter(val onSchoolItemClickListener: OnSchoolItemClickListener, val schoolData : ArrayList<Row> = arrayListOf()) : RecyclerView.Adapter<SchoolViewHolder>(){

    fun setData(schoolData : ArrayList<Row>) {
        this.schoolData.run {
            clear()
            addAll(schoolData)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchoolViewHolder
            = SchoolViewHolder(ItemSchoolBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: SchoolViewHolder, position: Int) {
        holder.bind(schoolData[position])
        holder.itemView.setOnClickListener {
            onSchoolItemClickListener.onClick(schoolData[position])
        }
    }

    override fun getItemCount(): Int = schoolData.size


}

class SchoolViewHolder(val view : ItemSchoolBinding) : RecyclerView.ViewHolder(view.root) {
    fun bind(data : Row) {
        view.schoolTitle.text = data.SCHUL_NM
        view.schoolPosition.text = data.ATPT_OFCDC_SC_NM
    }
}