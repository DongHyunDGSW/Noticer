package com.simple.noticer.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.simple.noticer.R
import com.simple.data.model.Row
import com.simple.noticer.ui.adapter.listener.onSchoolItemListener

class SchoolAdapter(val schoolData : ArrayList<Row>, val listener : onSchoolItemListener) : RecyclerView.Adapter<SchoolViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchoolViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_school, parent, false)

        return SchoolViewHolder(view)
    }

    override fun onBindViewHolder(holder: SchoolViewHolder, position: Int) {
        holder.schoolTitle.text = schoolData[position].SCHUL_NM
        holder.schoolEdu.text = schoolData[position].COEDU_SC_NM

        holder.itemView.setOnClickListener {
            listener.onClickItemListener(schoolData[position])
        }
    }

    override fun getItemCount(): Int {
        return schoolData.size
    }


}

class SchoolViewHolder(view : View) : RecyclerView.ViewHolder(view) {
    val schoolTitle = view.findViewById<TextView>(R.id.schoolName)
    val schoolEdu = view.findViewById<TextView>(R.id.schoolEdu)
}