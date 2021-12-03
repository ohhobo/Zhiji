package com.sdu.zhiji

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Button
import android.widget.Toast
import kotlin.math.sign
import kotlin.math.truncate

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button1 = findViewById<Button>(R.id.chat_button)
        button1.setOnClickListener {
                val intent = Intent(this, ChatActivity::class.java)
                startActivity(intent)
        }
        //点击button2 跳转至登录界面
        val button2 = findViewById<Button>(R.id.signin_button)
        button2.setOnClickListener {
            if (SignStatus.status == 1)
                Toast.makeText(this, "already sign in", Toast.LENGTH_SHORT)
            else {
                val intent = Intent(this, SigninActivity::class.java)
                startActivity(intent)
            }
        }
    }
}