package com.captsone.padicure.view.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.captsone.padicure.data.ListItemData
import com.captsone.padicure.data.Repository
import com.captsone.padicure.data.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(private val repository: Repository) : ViewModel() {
    private val _listData = MutableLiveData<ListItemData>()
    val listData: LiveData<ListItemData> = _listData

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message
    init {
        getListData()
    }
    private fun getListData(){
        viewModelScope.launch {
            when(val response = repository.getListData()){
                is Response.IsSuccessful<*> ->{
                    _listData.value =  response.data as ListItemData
                }
                is Response.IsError -> {
                    _message.value = response.message
                }
            }
        }

    }
}