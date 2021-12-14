package com.sdu.zhiji

import android.annotation.SuppressLint
import android.content.Intent
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.delay
import java.security.KeyStore
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.schedule
import kotlin.concurrent.thread
import kotlin.concurrent.timer

class ChatActivity : AppCompatActivity() {
    val msgList = ArrayList<Msg>()
    var adapter: MsgAdapter? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        val layoutManager = LinearLayoutManager(this)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView_chat)
        recyclerView.layoutManager = layoutManager
        adapter = MsgAdapter(msgList)
        recyclerView.adapter = adapter
        adapter!!.notifyItemInserted(msgList.size - 1)
        val button1 = findViewById<Button>(R.id.chat_send)
        button1.setOnClickListener {
            val editInput = findViewById<EditText>(R.id.chat_input)
            if (editInput.text.toString().isNotEmpty()) {
                val msg = Msg(editInput.text.toString(), Msg.type_sent)
                msgList.add(msg)
                recyclerView.scrollToPosition(msgList.size - 1)
                thread {
                    socket!!.sendR(editInput.text.toString())
                    editInput.setText("")
                }
            }
        }
        val handler = object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                Log.d("chatActivity", "msgHandler")
                recyclerView.scrollToPosition(msgList.size - 1)
            }
        }
        val handler1=object :Handler(Looper.getMainLooper()){
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                Log.d("chatActivity","prepare to exit")
                recyclerView.scrollToPosition(msgList.size-1)
                val intent=Intent(this@ChatActivity,MainActivity::class.java)
                startActivity(intent)
            }
        }
        var str: String? = null
        thread {
            while (true) {
                str = socket!!.recvR()
                if(str.toString()=="#exit"){
                    val msg=Msg("服务已经结束",Msg.type_received)
                    msgList.add(msg)
                    val message=Message.obtain()
                    handler1.sendMessage(message)
                }
                else if (str.toString().isNotEmpty()) {
                    Log.d("chatActivity", "msg:${str.toString()}")
                    val msg = Msg(str.toString(), Msg.type_received)
                    msgList.add(msg)
                    val message = Message.obtain()
                    handler.sendMessage(message)
                }
            }
        }
    }
}