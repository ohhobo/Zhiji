package com.sdu.zhiji.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sdu.zhiji.R

class recycle_Adapter_home (val recycleList:List<recycle_home>):
    RecyclerView.Adapter<recycle_Adapter_home.ViewHolder>() {

    inner class ViewHolder(view:View):RecyclerView.ViewHolder(view) {
        val imageId:ImageView=view.findViewById(R.id.recycle1)
        val text:TextView=view.findViewById(R.id.recycle_text1)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.home_recycle,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recyclelist=recycleList[position]
        holder.imageId.setImageResource(recyclelist.imageId)
        holder.text.text=recyclelist.text
    }

    override fun getItemCount(): Int {
        return recycleList.size
    }


}