package com.example.spinnerlist.ViewModel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DataViewModel: ViewModel() {


    var accountNumberSelected = MutableLiveData<Int>()


    var branchNumberSelected = MutableLiveData<Int>()


    var locationNumberSelected = MutableLiveData<Int>()

    fun setZero(){
        accountNumberSelected.value=0
        branchNumberSelected.value=0
        locationNumberSelected.value=0
    }

}