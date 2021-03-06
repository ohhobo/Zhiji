package com.sdu.zhiji

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import androidx.appcompat.app.ActionBar

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        //保持竖屏
       setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

        val button1:Button=findViewById(R.id.button1)
        val intent=Intent(this,MainActivity::class.java)
        var flag:Boolean=false //是否点击
        button1.setOnClickListener {
            startActivity(intent)
            finishAffinity()
            flag=true
        }
        //没点击跳过就等待五秒进入
        Handler().postDelayed({
            if(!flag)
            {
                startActivity(intent)
                finishAffinity()
            }},5000)



    }
}