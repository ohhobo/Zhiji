package com.sdu.zhiji

import android.content.Intent
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import java.io.File
import java.io.InputStream
import java.io.OutputStream
import java.lang.Exception
import kotlin.collections.ArrayList
import kotlin.concurrent.thread
import kotlin.coroutines.CoroutineContext

var socket: IConnect? = null

class ChatActivity : AppCompatActivity(), CoroutineScope {
    private lateinit var job: Job
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
    val msgList = ArrayList<Msg>()
    var adapter: MsgAdapter? = null
    private fun stringPro1(url:String,str: String): String {
        return when (str) {
            "#exit1" -> "$url${SignStatus.username}/resume1.pdf"
            "#exit2" -> "$url${SignStatus.username}/resume2.pdf"
            "#exit3" -> "$url${SignStatus.username}/resume3.pdf"
            "#exit4" -> "$url${SignStatus.username}/resume4.pdf"
            else -> str
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        var url: String = "http://81.68.226.148/files/"
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
                    file.createNewFile()
                    val response = GitHubService.getInstance().downloadFile(url).execute()
                    val body = response.body()
                    if (response.isSuccessful && body != null) {
                        var inStream: InputStream? = null
                        var outStream: OutputStream? = null
                        try {
                            inStream = body.byteStream()
                            outStream = file.outputStream()
                            val buff = ByteArray(1024)
                            var len = inStream.read(buff)
                            while (len != -1) {
                                outStream.write(buff, 0, len)
                                len = inStream.read(buff)
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        } finally {
                            inStream?.close()
                            outStream?.close()
                            socket?.disconnect()
                            val intent = Intent()
                            intent.putExtra("return", "hello hello")
                            setResult(RESULT_OK, intent)
                            finish()
                        }
                    }
                }
            }
        }
        thread {
            if(socket==null)
                socket = IConnect("81.68.226.148", 90)
            while (true) {
                val str: String? = socket!!.recvR()
                if (str.toString() == "#exit1" || str.toString() == "#exit2"
                    || str.toString() == "#exit3" || str.toString() == "#exit4"
                ) {
                    url = stringPro1(url,str.toString())
                    Log.i("chatActivity", "url is $url")
                    msgList.add(Msg("服务已经结束", Msg.type_received))
                    handler1.sendMessage(Message.obtain())
                } else if (str.toString().isNotEmpty() && str.toString() != "null") {
                    Log.d("chatActivity", "msg:${str.toString()}")
                    msgList.add(Msg(str.toString(), Msg.type_received))
                    handler.sendMessage(Message.obtain())
                }
            }
        }
    }
}