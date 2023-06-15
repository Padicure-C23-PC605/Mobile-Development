package com.captsone.padicure.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.captsone.padicure.data.ItemData
import com.captsone.padicure.data.Repository
import com.captsone.padicure.data.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _data = MutableLiveData<ItemData>()
    val data: LiveData<ItemData> = _data

    fun getItemData(id: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            when (val response = repository.getDetailData(id)) {
                is Response.IsSuccessful<*> -> {
                    _data.value = response.data as ItemData
                }

                is Response.IsError -> {
                    _message.value = response.message
                }
            }
            _isLoading.value = false
        }
    }
}