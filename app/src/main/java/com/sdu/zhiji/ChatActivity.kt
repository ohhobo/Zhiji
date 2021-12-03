package com.sdu.zhiji

import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.concurrent.thread

class ChatActivity : AppCompatActivity() {
    private val msgList = ArrayList<Msg>()
    private var adapter: MsgAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        val layoutManager = LinearLayoutManager(this)
        val recyclerView=findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager=layoutManager
        adapter= MsgAdapter(msgList)
        recyclerView.adapter=adapter
        var socket: IConnect? = null
        thread {
            socket = IConnect("81.68.226.148", 90)
            Log.d("chatActivity", "successful connecting")
            val button1 = findViewById<Button>(R.id.chat_send)
            button1.setOnClickListener {
                val editInput = findViewById<EditText>(R.id.chat_input)
                if(editInput.text.toString().isNotEmpty()) {
                    val msg = Msg(editInput.text.toString(), Msg.type_sent)
                    msgList.add(msg)
                    adapter!!.notifyItemInserted(msgList.size - 1)
                    recyclerView.scrollToPosition(msgList.size - 1)
                    thread {
                        socket!!.sendR(editInput.text.toString())
                        editInput.setText("")
                    }
                }
            }
            thread {
                while (true) {
                    val str: String? = socket!!.recvR()
                    if (str != null)
                        Log.d("chatActivity", "msg:$str")
                }
            }
        }
    }
}