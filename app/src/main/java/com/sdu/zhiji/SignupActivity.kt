package com.sdu.zhiji

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

val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl("http://81.68.226.148/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

class App(val code: String, val username: String, val password: String)
interface AppService {
    @GET("api/signup.php")
    fun getAppData(@Query("username") para_u: String, @Query("password") para_p: String): Call<App>
}

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("byr", "im here")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        val button1 = findViewById<Button>(R.id.signup_submit)
        Log.d("byr", "im here1")
        button1.setOnClickListener {
            Log.d("byr", "im here2")
            val username = findViewById<EditText>(R.id.signup_username).text.toString()
            val password = findViewById<EditText>(R.id.signup_password).text.toString()
            val appService = retrofit.create(AppService::class.java)
            appService.getAppData(username, password).enqueue(object : Callback<App> {
                override fun onResponse(call: Call<App>, response: Response<App>) {
                    Log.d("byr", "im here3")
                    val list = response.body()
                    if (list != null) {
                        Log.d("SignupActivity", "return_code:${list.code}")
                        Log.d("SignupActivity", "return_username:${list.username}")
                        Log.d("SignupActivity", "return_password${list.password}")
                    }
                }

                override fun onFailure(call: Call<App>, t: Throwable) {
                    Log.d("byr", "im here4")
                    t.printStackTrace()
                }
            })
        }
    }
}