package com.lordsam.mykart.ui.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SoldProductsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is sold product Fragment"
    }
    val text: LiveData<String> = _text
}