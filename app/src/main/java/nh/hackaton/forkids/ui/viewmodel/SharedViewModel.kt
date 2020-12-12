package nh.hackaton.forkids.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import nh.hackaton.forkids.ui.base.BaseViewModel

class SharedViewModel : BaseViewModel(){
    val name = MutableLiveData<String>()
    val name_livedata : LiveData<String>
        get() = name

    val pw = MutableLiveData<String>()
    val pw_livedata : LiveData<String>
        get() = pw

    val reg = MutableLiveData<String>()
    val reg_livedata : LiveData<String>
        get() = reg

    val phone = MutableLiveData<String>()
    val phone_livedata : LiveData<String>
        get() = phone

    val type = MutableLiveData<Int>()
    val type_livedata : LiveData<Int>
        get() = type



}