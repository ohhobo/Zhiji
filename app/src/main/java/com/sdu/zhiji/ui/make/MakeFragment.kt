package com.sdu.zhiji.ui.make

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sdu.zhiji.databinding.FragmentMakeBinding

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

        val textView: TextView = binding.textMake
        makeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}