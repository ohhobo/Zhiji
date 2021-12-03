package com.sdu.zhiji

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import kotlin.concurrent.thread

class ChatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        var socket:IConnect?=null
        thread {
            socket = IConnect("81.68.226.148", 90)
            Log.d("chatActivity", "successful connecting")
            val button1 = findViewById<Button>(R.id.chat_send)
            button1.setOnClickListener {
                val editInput = findViewById<EditText>(R.id.chat_input)
                thread {
                    socket!!.sendR(editInput.text.toString())
                    editInput.setText("")
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