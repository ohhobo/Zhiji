package com.sdu.zhiji.ui.make

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sdu.zhiji.ChatActivity
import com.sdu.zhiji.R
import com.sdu.zhiji.databinding.FragmentMakeBinding
import com.sdu.zhiji.MBTI.mbti_MainActivity
import com.sdu.zhiji.SignStatus

class MakeFragment : Fragment() {

    private lateinit var makeViewModel: MakeViewModel
    private var _binding: FragmentMakeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        makeViewModel =
            ViewModelProvider(this).get(MakeViewModel::class.java)

        _binding = FragmentMakeBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textMake
//        makeViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })

        val progressbar: ProgressBar = root.findViewById(R.id.progressBar_make)
        val text: TextView = root.findViewById(R.id.textView_make3)
        val image_ps: ImageView = root.findViewById(R.id.ps_image)
        text.setOnClickListener {
            if (progressbar.progress != 100) {
                progressbar.progress = progressbar.progress + 10
            } else {
                progressbar.progress = 0
            }

        }
        progressbar.setOnClickListener {
            if (progressbar.progress != 100) {
                progressbar.progress = progressbar.progress + 10
            } else {
                progressbar.progress = 0
            }

        }
        image_ps.setOnClickListener {
            if (progressbar.progress != 100) {
                progressbar.progress = progressbar.progress + 10
            } else {
                progressbar.progress = 0
            }

        }

        //MBTI问卷
        val intent_mbti = Intent(activity, mbti_MainActivity::class.java)
        val mbti: ImageView = root.findViewById(R.id.make_top_image)
        mbti.setOnClickListener {
            startActivity(intent_mbti)
        }

        val button_psychological_test: Button = root.findViewById(R.id.button_psychological_test)
        button_psychological_test.setOnClickListener {
            startActivity(intent_mbti)
        }
        //简历
        val button_resume = root.findViewById<Button>(R.id.button_resume)
        button_resume.setOnClickListener {
            if (SignStatus.status == 1) {
                val intent_resume = Intent(activity, ChatActivity::class.java)
                startActivityForResult(intent_resume, 1)
            } else
                Toast.makeText(activity, "sign in first", Toast.LENGTH_SHORT).show()
        }
        return root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            1 -> if (resultCode == RESULT_OK) {
                //放那个弹出界面捏
                Log.i("chatActivity", "received")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}