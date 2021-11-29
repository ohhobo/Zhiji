package com.sdu.zhiji.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sdu.zhiji.R
import com.sdu.zhiji.databinding.FragmentHomeBinding
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import com.youth.banner.listener.OnBannerListener
import com.youth.banner.loader.ImageLoader

class HomeFragment : Fragment() ,OnBannerListener{

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val recyclelist=ArrayList<recycle_home>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val banner1: Banner =root.findViewById(R.id.banner1)
        initView(banner1)
//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })

        initRecycle()//初始化
        val layerManager= LinearLayoutManager(activity)
        val recycle_home: RecyclerView =root.findViewById(R.id.recycle_home)
        recycle_home.layoutManager=layerManager
        val adapter=recycle_Adapter_home(recyclelist)
        recycle_home.adapter=adapter


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initView(banner:Banner) {
        var list_path: ArrayList<String>? = null
        var list_title: ArrayList<String>? = null

        //放图片地址的集合
        list_path = ArrayList<String>()
        //放标题的集合
        list_title = ArrayList<String>()

        list_path.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fbpic.588ku.com%2Fillus_water_img%2F20%2F11%2F07%2Fbe94e909b97d71d913de6fa141cf58a7.jpg&refer=http%3A%2F%2Fbpic.588ku.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1640339254&t=ecf2f81f57111856569a473878a66e6f");
        list_path.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fningdu.9ihome.com%2Finfo%2Fa9iHome%2FUploadFiles_6616%2F201909%2F2019091217121058.gif&refer=http%3A%2F%2Fningdu.9ihome.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1640339191&t=0638e58fcd698c5b8445a096be3b542c");
        list_path.add("https://img0.baidu.com/it/u=1414162302,620997781&fm=26&fmt=auto");

        list_title.add("让找工作更简单");
        list_title.add("中秋快乐");
        list_title.add("个人简历");


        //设置内置样式，共有六种可以点入方法内逐一体验使用。
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
        //设置图片加载器，图片加载器在下方
        banner.setImageLoader(MyLoader());
        //设置图片网址或地址的集合
        banner.setImages(list_path);
        //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
        banner.setBannerAnimation(Transformer.Default);
        //设置轮播图的标题集合
        banner.setBannerTitles(list_title);
        //设置轮播间隔时间
        banner.setDelayTime(3000);
        //设置是否为自动轮播，默认是“是”。
        banner.isAutoPlay(true);
        //设置指示器的位置，小点点，左中右。
        banner.setIndicatorGravity(BannerConfig.CENTER)
            //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
            .setOnBannerListener(this)
            //必须最后调用的方法，启动轮播图。
            .start();
    }


    override fun OnBannerClick(position: Int) {
        Toast.makeText(getActivity(), "你点了第" + (position + 1) + "张轮播图", Toast.LENGTH_SHORT).show()
    }
    //自定义的图片加载器
    private inner class MyLoader : ImageLoader() {
        override fun displayImage(context: Context, path: Any, imageView: ImageView) {
            Glide.with(context).load(path as String).into(imageView)
        }
    }

    private fun initRecycle(){
        recyclelist.add(recycle_home(R.drawable.recycle_home1,"政策规划 | 假如你是胡晶晶，遭遇变相裁员怎么办？"))
        recyclelist.add(recycle_home(R.drawable.recycle_home2,"职业科普 | 通信专业就业方向有哪些？"))
        recyclelist.add(recycle_home(R.drawable.recycle_home3,"职业穿搭 | 职业新人如何穿出自信？"))
    }

}