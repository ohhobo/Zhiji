package com.sdu.zhiji

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.io.InputStreamReader
import java.net.Socket
import okhttp3.ResponseBody
import retrofit2.http.Path
import retrofit2.http.Streaming
import retrofit2.http.Url
import com.google.gson.annotations.SerializedName
data class Repo(
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("description")
    val description: String?
)

interface GitHubService {
    @GET("users/{user}/repos")
    fun getListRepos(@Path("user") user: String): Call<List<Repo>>
    @Streaming
    @GET
    fun downloadFile(@Url url: String): Call<ResponseBody>
    companion object {
        @Volatile
        private var instance: GitHubService? = null
        fun getInstance(): GitHubService = instance ?: synchronized(GitHubService::class.java) {
            instance ?: Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GitHubService::class.java)
                .also { instance = it }
        }
    }
}

val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl("http://81.68.226.148/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

object SignStatus {
    var status: Int = 0
    var username: String = ""
}

class Msg(val content: String, val type: Int) {
    companion object {
        const val type_received = 0
        const val type_sent = 1
    }
}

class MsgAdapter(val msglist: List<Msg>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    inner class LeftViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val leftMsg: TextView = view.findViewById(R.id.leftMsg)
    }

    inner class RightViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val rightMsg: TextView = view.findViewById(R.id.rightMsg)
    }

    override fun getItemViewType(position: Int): Int {
        val msg = msglist[position]
        return msg.type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        if (viewType == Msg.type_received) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.msg_left, parent, false)
            LeftViewHolder(view)
        } else {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.msg_right, parent, false)
            RightViewHolder(view)
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val msg = msglist[position]
        when (holder) {
            is LeftViewHolder -> holder.leftMsg.text = msg.content
            is RightViewHolder -> holder.rightMsg.text = msg.content
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemCount(): Int {
        return msglist.size
    }
}

class IConnect(ipAddress: String, port: Int) {
    private val ip = ipAddress
    private val p = port
    private var sc: Socket? = null
    private var status: Int = -1

    init {
        sc = Socket(ip, p)
        if (sc != null) {
            Log.d("iconnect", "connect server successfully")
        } else {
            Log.d("iconnect", "connect fail now retry")
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
        Log.i("chatActivity","1")
        val inStream = sc!!.getInputStream()
        Log.i("chatActivity","2")
        val dataIn = InputStreamReader(inStream, "gb2312")
        Log.i("chatActivity","3")
        val inMessage = CharArray(1024)
        Log.i("chatActivity","4")
        val a = dataIn.read(inMessage)
        Log.i("chatActivity","5")
        if (a <= -1)
            return null
        return String(inMessage, 0, a)
    }
    fun disconnect(){
        sc?.close()
        sc=null
    }
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