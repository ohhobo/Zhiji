package com.sdu.zhiji

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import kotlin.math.log
import android.content.Intent
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.graphics.scaleMatrix
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


class SignInApp(val code: String)
interface SignInService {
    @GET("api/signin.php")
    fun getAppData(
        @Query("username") para_u: String,
        @Query("password") para_p: String
    ): Call<SignInApp>
}

class SigninActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        val button1 = findViewById<Button>(R.id.signin_button_submit)
        button1.setOnClickListener {
            val username = findViewById<EditText>(R.id.signin_username).text.toString()
            val password = findViewById<EditText>(R.id.signin_password).text.toString()
            val appService = retrofit.create(SignInService::class.java)
            appService.getAppData(username, password).enqueue(object : Callback<SignInApp> {
                override fun onResponse(call: Call<SignInApp>, response: Response<SignInApp>) {
                    val list = response.body()
                    if (list != null && list.code == "1") {
                        Toast.makeText(
                            this@SigninActivity,
                            "sign in successfully!",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        val edit1 = findViewById<EditText>(R.id.signin_password).setText("")
                        val edit2 = findViewById<EditText>(R.id.signin_username).setText("")
                        Toast.makeText(
                            this@SigninActivity,
                            "username or password wrong",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<SignInApp>, t: Throwable) {
                    Log.d("signinError", "sign in error")
                    t.printStackTrace()
                }
            })
        }
        val button2 = findViewById<Button>(R.id.signin_button_signup)
    }
}