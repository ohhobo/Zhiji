package com.sdu.zhiji.ui.chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sdu.zhiji.R

class recycle_Adapter_chat (val recycleList:List<recycle_chat>):
    RecyclerView.Adapter<recycle_Adapter_chat.ViewHolder>() {

    inner class ViewHolder(view:View):RecyclerView.ViewHolder(view) {
        val text:TextView=view.findViewById(R.id.text_chat_recycle)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.chat_recycle,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recyclelist=recycleList[position]
        holder.text.text=recyclelist.text
    }

    override fun getItemCount(): Int {
        return recycleList.size
    }


}