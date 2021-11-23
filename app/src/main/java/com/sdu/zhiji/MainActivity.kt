package com.sdu.zhiji

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Button
import android.widget.Toast
import kotlin.math.truncate

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //点击button1 跳转至注册界面
        val button1 = findViewById<Button>(R.id.signup_button)
        button1.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
        //点击button2 跳转至登录界面
        val button2 = findViewById<Button>(R.id.signin_button)
        button2.setOnClickListener {
            val intent = Intent(this, SigninActivity::class.java)
            startActivity(intent)
        }
    }
}