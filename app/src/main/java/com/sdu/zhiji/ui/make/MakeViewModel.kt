package com.sdu.zhiji.ui.make

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MakeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is make Fragment"
    }
    val text: LiveData<String> = _text
}