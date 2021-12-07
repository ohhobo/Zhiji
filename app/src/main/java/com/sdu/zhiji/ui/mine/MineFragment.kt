package com.sdu.zhiji.ui.mine

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sdu.zhiji.R
import com.sdu.zhiji.SignStatus
import com.sdu.zhiji.SigninActivity
import com.sdu.zhiji.SignupActivity
import com.sdu.zhiji.databinding.FragmentMineBinding

class MineFragment : Fragment() {

    private lateinit var mineViewModel: MineViewModel
    private var _binding: FragmentMineBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val recyclelist1=ArrayList<recycle_mine1>()
    private val recyclelist2=ArrayList<recycle_mine2>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mineViewModel =
            ViewModelProvider(this).get(MineViewModel::class.java)

        _binding = FragmentMineBinding.inflate(inflater, container, false)
        val root: View = binding.root


//        val textView: TextView = binding.textMine
//        mineViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })


        initRecycle1()//初始化
        val layerManager1= LinearLayoutManager(activity)
        val mine_recycle1: RecyclerView =root.findViewById(R.id.mine_recycle1)
        mine_recycle1.layoutManager=layerManager1
        val adapter1= recycle_Adapter_mine1(recyclelist1)
        mine_recycle1.adapter=adapter1

        initRecycle2()
        val layerManager2= LinearLayoutManager(activity)
        layerManager2.orientation=LinearLayoutManager.HORIZONTAL
        val mine_recycle2: RecyclerView =root.findViewById(R.id.mine_recycle2)
        mine_recycle2.layoutManager=layerManager2
        val adapter2= recycle_Adapter_mine2(recyclelist2)
        mine_recycle2.adapter=adapter2

        val button_attention:Button=root.findViewById(R.id.mine_attention)
        button_attention.setOnClickListener{
            button_attention.text=(button_attention.text.toString().toInt()+1).toString()
        }

        val button_fans:Button=root.findViewById(R.id.mine_fans)
        button_fans.setOnClickListener{
            button_fans.text=(button_fans.text.toString().toInt()+1).toString()
        }

        val button_likes:Button=root.findViewById(R.id.mine_likes)
        button_likes.setOnClickListener{
            button_likes.text=(button_likes.text.toString().toInt()+1).toString()
        }

        //登录
        val intent_signin = Intent(activity, SigninActivity::class.java)
        val button_signin = root.findViewById<ImageView>(R.id.mine_image_top1)
        button_signin.setOnClickListener {
            if (SignStatus.status == 1)
                Toast.makeText(activity, "already sign in", Toast.LENGTH_SHORT)
            else {
                startActivity(intent_signin)
            }
        }

        val image_signin:ImageView=root.findViewById(R.id.image_signin)
        image_signin.setOnClickListener {
            if (SignStatus.status == 1)
                Toast.makeText(activity, "already sign in", Toast.LENGTH_SHORT)
            else {
                startActivity(intent_signin)
            }
        }

        //注册
        val intent_signup = Intent(activity, SignupActivity::class.java)
        val image_signup:ImageView=root.findViewById(R.id.image_signup)
        image_signup.setOnClickListener {
            if (SignStatus.status == 1)
                Toast.makeText(activity, "already sign in", Toast.LENGTH_SHORT)
            else {
                startActivity(intent_signup)
            }
        }


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initRecycle1(){
        recyclelist1.add(recycle_mine1(R.drawable.recycle_mine1_1,"我收藏的文手"))
        recyclelist1.add(recycle_mine1(R.drawable.recycle_mine1_2,"我发布的提问"))
        recyclelist1.add(recycle_mine1(R.drawable.recycle_mine1_3,"我的回答"))
        recyclelist1.add(recycle_mine1(R.drawable.recycle_mine1_4,"我收藏的产品"))
        recyclelist1.add(recycle_mine1(R.drawable.recycle_mine1_5,"我的订单管理"))
        recyclelist1.add(recycle_mine1(R.drawable.recycle_mine1_6,"职记人工客服"))
    }

    private fun initRecycle2(){
        recyclelist2.add(recycle_mine2(R.drawable.mine_achievement2,"第一次实习"))
        recyclelist2.add(recycle_mine2(R.drawable.mine_achievement3,"第一分简历"))
        recyclelist2.add(recycle_mine2(R.drawable.mine_achievement4,"第一份工资"))
        recyclelist2.add(recycle_mine2(R.drawable.mine_achievement1,"第一天上班"))
    }


}