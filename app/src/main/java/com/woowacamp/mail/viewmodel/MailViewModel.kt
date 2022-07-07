package com.woowacamp.mail.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MailViewModel: ViewModel() {

    val type = MutableLiveData<Int>(0)

    fun changeType(type: Int) {
        this.type.postValue(type)
    }
}

class MailViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MailViewModel::class.java)) {
            return MailViewModel() as T
        }
        throw IllegalAccessException("Unkown Viewmodel Class")
    }
}