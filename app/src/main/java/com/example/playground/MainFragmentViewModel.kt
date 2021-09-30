package com.example.playground

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainFragmentViewModel : ViewModel() {

    private val _mainFragmentLiveData = MutableLiveData<Int>()
    val mainFragmentLiveData: LiveData<Int> = _mainFragmentLiveData

    fun changeValue(newValue: Int) {
        _mainFragmentLiveData.value = newValue
    }

}