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
import java.io.InputStreamReader
import java.net.Socket
import java.util.*
import kotlin.concurrent.thread

val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl("http://81.68.226.148/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

object SignStatus {
    var status: Int = 0
    var username: String = ""
}

class IConnect(ipAddress: String, port: Int) {
    private val ip = ipAddress
    private val p = port
    private var sc: Socket? = null
    private var status: Int = -1

    init {
        sc = Socket(ip, p)
        if (sc != null) {
            Log.d("iconnect","connect server successfully")
        } else {
            Log.d("iconnect","connect fail now retry")
            while (!retry()) {
            }
        }
    }

    private fun retry(): Boolean {
        if (sc == null) {
            sc = Socket(ip, p)
            return sc != null
        }
        return true
    }

    fun sendR(req: String) {
        val outStream = sc!!.getOutputStream()
        val msg = req.toByteArray()
        outStream.write(msg)
        outStream.flush()
    }

    fun recvR(): String? {
        val inStream = sc!!.getInputStream()
        val dataIn = InputStreamReader(inStream, "gb2312")
        val inMessage = CharArray(1024)
        val a = dataIn.read(inMessage)
        if (a <= -1)
            return null
        return String(inMessage, 0, a)
    }

//    fun mainProcess() {
//        thread {
//            val scan = Scanner(System.`in`)
//            while (true) {
//                val string: String = scan.nextLine()
//                sendR(string);
//            }
//        }
//        thread {
//            while (true) {
//                val string: String? = recvR()
//                if (string != null)
//                    println(string)
//                else continue
//            }
//        }
//    }
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
