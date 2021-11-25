package com.sdu.zhiji

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import kotlin.math.log

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        val button1=findViewById<Button>(R.id.signup_submit)
        button1.setOnClickListener {
            val username=findViewById<EditText>(R.id.signup_username).text.toString()
            val password=findViewById<EditText>(R.id.signup_password).text.toString()
            println("username is $username password is $password")
        }
    }
}