package com.sdu.zhiji

import android.annotation.SuppressLint
import android.content.Intent
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import okhttp3.Dispatcher
import retrofit2.http.Url
import java.io.File
import java.io.InputStream
import java.io.OutputStream
import java.lang.Exception
import java.security.KeyStore
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.schedule
import kotlin.concurrent.thread
import kotlin.concurrent.timer
import kotlin.coroutines.CoroutineContext

class ChatActivity : AppCompatActivity(), CoroutineScope {
    private lateinit var job: Job
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
    val msgList = ArrayList<Msg>()
    var adapter: MsgAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        val url = "http://81.68.226.148/files/pdffiles/pdftest.pdf"
        val url2 = "http://81.68.226.148/files/wordfiles/wordtest.docx"
        super.onCreate(savedInstanceState)
        job = Job()
        setContentView(R.layout.activity_chat)
        val layoutManager = LinearLayoutManager(this)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView_chat)
        recyclerView.layoutManager = layoutManager
        adapter = MsgAdapter(msgList)
        recyclerView.adapter = adapter
        adapter!!.notifyItemInserted(msgList.size - 1)
        val button1 = findViewById<Button>(R.id.chat_send)
        button1.setOnClickListener {
            val editInput = findViewById<EditText>(R.id.chat_input)
            if (editInput.text.toString().isNotEmpty()) {
                val msg = Msg(editInput.text.toString(), Msg.type_sent)
                msgList.add(msg)
                recyclerView.scrollToPosition(msgList.size - 1)
                thread {
                    socket!!.sendR(editInput.text.toString())
                    editInput.setText("")
                }
            }
        }
        val handler = object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                Log.d("chatActivity", "msgHandler")
                recyclerView.scrollToPosition(msgList.size - 1)
            }
        }
        val handler1 = object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                recyclerView.scrollToPosition(msgList.size - 1)
                launch(Dispatchers.IO) {
                    val file =
                        File(
                            "${Environment.getExternalStorageDirectory().path}/Download/${
                                url.substringAfterLast(
                                    "/"
                                )
                            }"
                        )
                    val file2 =
                        File(
                            "${Environment.getExternalStorageDirectory().path}/Download/${
                                url2.substringAfterLast(
                                    "/"
                                )
                            }"
                        )
                    file.createNewFile()
                    file2.createNewFile()
                    val response = GitHubService.getInstance().downloadFile(url).execute()
                    val body = response.body()
                    val response2 = GitHubService.getInstance().downloadFile(url2).execute()
                    val body2 = response2.body()
                    if (response.isSuccessful && body != null && response2.isSuccessful && body2 != null) {
                        var inStream: InputStream? = null
                        var outStream: OutputStream? = null
                        var inStream2: InputStream? = null
                        var outStream2: OutputStream? = null
                        try {
                            inStream = body.byteStream()
                            outStream = file.outputStream()
                            inStream2 = body2.byteStream()
                            outStream2 = file2.outputStream()
                            val buff = ByteArray(1024)
                            val buff2 = ByteArray(1024)
                            var len = inStream.read(buff)
                            var len2 = inStream2.read(buff2)
                            while (len != -1 || len2 != -1) {
                                if (len != -1) {
                                    outStream.write(buff, 0, len)
                                    len = inStream.read(buff)
                                }
                                if (len2 != -1) {
                                    outStream2.write(buff2, 0, len2)
                                    len2 = inStream2.read(buff2)
                                }
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        } finally {
                            inStream?.close()
                            inStream2?.close()
                            outStream?.close()
                            outStream2?.close()
                            val intent = Intent(this@ChatActivity, MainActivity::class.java)
                            startActivity(intent)
                        }
                    }
                }
            }
        }
        var str: String? = null
        thread {
            while (true) {
                str = socket!!.recvR()
                if (str.toString() == "#exit") {
                    val msg = Msg("服务已经结束", Msg.type_received)
                    msgList.add(msg)
                    val message = Message.obtain()
                    handler1.sendMessage(message)
                } else if (str.toString().isNotEmpty()) {
                    Log.d("chatActivity", "msg:${str.toString()}")
                    val msg = Msg(str.toString(), Msg.type_received)
                    msgList.add(msg)
                    val message = Message.obtain()
                    handler.sendMessage(message)
                }
            }
        }
    }
}