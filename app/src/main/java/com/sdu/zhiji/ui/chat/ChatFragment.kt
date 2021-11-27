package com.sdu.zhiji.ui.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sdu.zhiji.R
import com.sdu.zhiji.databinding.FragmentChatBinding
import com.sdu.zhiji.ui.home.recycle_Adapter_home
import com.sdu.zhiji.ui.home.recycle_home

class ChatFragment : Fragment() {

    private lateinit var chatViewModel: ChatViewModel
    private var _binding: FragmentChatBinding? = null
    private val recyclelist=ArrayList<recycle_chat>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        chatViewModel =
            ViewModelProvider(this).get(ChatViewModel::class.java)

        _binding = FragmentChatBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textChat
//        chatViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })

        initRecycle()//初始化
        val layerManager= LinearLayoutManager(activity)
        val recycle_chat: RecyclerView =root.findViewById(R.id.recycle_chat)
        recycle_chat.layoutManager=layerManager
        val adapter= recycle_Adapter_chat(recyclelist)
        recycle_chat.adapter=adapter
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initRecycle(){
        recyclelist.add(recycle_chat("# 济南公安通报阿里女员工被侵案"))
        recyclelist.add(recycle_chat("# 校园招聘、实习招聘、秋招的区别是什么？"))
        recyclelist.add(recycle_chat("# 无实习经历的应届生如何面对秋招？"))
        recyclelist.add(recycle_chat("# 作为2020届毕业生的你，工资多少？"))
        recyclelist.add(recycle_chat("# 如何使自己日常工作生活条理清晰明了？"))
        recyclelist.add(recycle_chat("# 你有什么经验一定要分享给初入职场的新人？"))
        recyclelist.add(recycle_chat("# 对于职场新人来说，有哪些坑一定不能踩？"))
        recyclelist.add(recycle_chat("# 初入职场觉得自己什么都不会，心态不好如何调整？"))
        recyclelist.add(recycle_chat("# 初入职场觉得自己什么都不会，心态不好如何调整？"))
        recyclelist.add(recycle_chat("# 初入职场觉得自己什么都不会，心态不好如何调整？"))
        recyclelist.add(recycle_chat("# 初入职场觉得自己什么都不会，心态不好如何调整？"))
        recyclelist.add(recycle_chat("# 假如你是胡晶晶，遭遇变相裁员怎么办？"))
        recyclelist.add(recycle_chat("# 通信专业就业方向有哪些？"))
        recyclelist.add(recycle_chat("# 职业新人如何穿出自信？"))
    }
}