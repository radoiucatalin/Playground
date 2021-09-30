package com.example.playground

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _liveData = MutableLiveData<String>().apply {
        value = "first"
    }
    val liveData: LiveData<String> = _liveData

    fun changeValue(value: String) {
        _liveData.value = value
    }
}