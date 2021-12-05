package com.sdu.zhiji

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import kotlin.math.sign


class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        val button1 = findViewById<Button>(R.id.signup_submit)
        button1.setOnClickListener {
            val username = findViewById<EditText>(R.id.signup_username).text.toString()
            val password = findViewById<EditText>(R.id.signup_password).text.toString()
            val appService = retrofit.create(SignUpService::class.java)
            appService.getAppData(username, password).enqueue(object : Callback<SignUpApp> {
                override fun onResponse(call: Call<SignUpApp>, response: Response<SignUpApp>) {
                    val list = response.body()
                    if (list != null && list.code == "1") {
                        SignStatus.status = 1
                        SignStatus.username = username
                        val intent = Intent(this@SignupActivity, MainActivity::class.java)
                        startActivity(intent)
                    }
                }

                override fun onFailure(call: Call<SignUpApp>, t: Throwable) {
                    Log.d("signupError", "sign up error")
                    t.printStackTrace()
                }
            })
        }
    }
}