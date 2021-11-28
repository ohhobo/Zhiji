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

val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl("http://81.68.226.148/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()
object SignStatus{
    var status:Int=0
}
//api for sign_in
class SignInApp(val code: String)
interface SignInService {
    @GET("api/signin.php")
    fun getAppData(
        @Query("username") para_u: String,
        @Query("password") para_p: String
    ): Call<SignInApp>
}
//api for sign_up
class SignUpApp(val code: String, val username: String, val password: String)
interface SignUpService {
    @GET("api/signup.php")
    fun getAppData(
        @Query("username") para_u: String,
        @Query("password") para_p: String
    ): Call<SignUpApp>
}