package com.captsone.padicure.view.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.captsone.padicure.data.Repository
import com.captsone.padicure.data.Response
import com.captsone.padicure.data.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel
@Inject constructor(private val repository: Repository) :
    ViewModel() {
    private val _userData = MutableLiveData<UserData>()
    val userData: LiveData<UserData> = _userData

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message
    

    init {
        getUserData()
    }
    private fun getUserData() {
        viewModelScope.launch {
            _isLoading.value = true
            when (val response = repository.getUserData()){
                is Response.IsSuccessful<*> -> {
                    _userData.value = response.data as UserData
                }
                is Response.IsError -> {
                    _message.value = response.message
                }

            }
            _isLoading.value = false
        }
    }

    fun logout(){
        viewModelScope.launch {
            _isLoading.value = true
            when (val response = repository.logout()){
                is Response.IsSuccessful<*> -> {
                    _message.value = response.data as String
                }
                else -> {}
            }
            _isLoading.value = false
        }
    }
}