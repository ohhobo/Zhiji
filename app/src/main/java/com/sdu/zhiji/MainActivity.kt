package com.sdu.zhiji

import android.Manifest
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.SearchView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.sdu.zhiji.databinding.ActivityMainBinding
import kotlin.concurrent.thread

const val REQUEST_WRITE_EXTERNAL_STORAGE=1
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        SignStatus.status=1
        SignStatus.username="18654669393"
        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_chat, R.id.navigation_make,R.id.navigation_mine
            )
        )
        //setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        //toolbar
        val toolbar1:Toolbar=findViewById(R.id.toolBar1)
        toolbar1.title=" "
        setSupportActionBar(toolbar1)
        //搜索框
        val search1: SearchView =findViewById(R.id.search1)
        search1.setIconifiedByDefault(false)
        search1.setQueryHint("大家都在搜...")
        //通知按钮
        val notice: ImageView =findViewById(R.id.imageButton1)
        //保持竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        Log.i("mainActivity", Environment.DIRECTORY_DOWNLOADS)
        requestPermissions(
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
            REQUEST_WRITE_EXTERNAL_STORAGE
        )

    }

}