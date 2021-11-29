package com.sdu.zhiji.ui.mine

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sdu.zhiji.R

class recycle_Adapter_mine1 (val recycleList:List<recycle_mine1>):
    RecyclerView.Adapter<recycle_Adapter_mine1.ViewHolder>() {

    inner class ViewHolder(view:View):RecyclerView.ViewHolder(view) {
        val imageId:ImageView=view.findViewById(R.id.recycle1_mine_image)
        val text:TextView=view.findViewById(R.id.recycle1_mine_text)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.mine_recycle1,parent,false)
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