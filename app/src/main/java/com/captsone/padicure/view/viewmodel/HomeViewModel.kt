package com.captsone.padicure.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.captsone.padicure.data.ListItemData
import com.captsone.padicure.data.Repository
import com.captsone.padicure.data.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(private val repository: Repository) : ViewModel() {
    private val _listData = MutableLiveData<ListItemData>()
    val listData: LiveData<ListItemData> = _listData

    init {
        getListData()
    }
    private fun getListData(){
        viewModelScope.launch {
            when(val response = repository.getListData()){
                is Response.IsSuccessful<*> ->{
                    _listData.value = response.data as ListItemData
                }
                else -> {}
            }
        }

    }
}