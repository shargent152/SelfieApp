package com.example.selfieapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.storage.StorageReference

class storageViewModel : ViewModel(){
    val link = MutableLiveData<StorageReference>()

    val selectedLink: LiveData<StorageReference> get() = link

    fun selectLink(item: StorageReference){
        link.value
    }
}