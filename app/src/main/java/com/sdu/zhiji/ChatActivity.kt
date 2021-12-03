package com.sdu.zhiji

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import java.security.Policy
import kotlin.concurrent.thread

class ChatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        thread {
            val socket=IConnect("81.68.226.148",90)
        }
    }
}