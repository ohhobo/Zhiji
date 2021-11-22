package com.sdu.zhiji

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        val button1:Button=findViewById(R.id.button1)
        val intent=Intent(this,MainActivity::class.java)
        Handler().postDelayed({startActivity(intent)},5000)

        button1.setOnClickListener {
            startActivity(intent)
        }
    }
}