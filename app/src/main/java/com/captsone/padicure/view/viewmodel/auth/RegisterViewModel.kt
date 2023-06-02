package com.captsone.padicure.view.viewmodel.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.captsone.padicure.data.Repository
import com.captsone.padicure.data.Response
import com.captsone.padicure.data.SignInUser
import com.captsone.padicure.data.SignUpUser
import com.google.firebase.auth.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: Repository
)
    : ViewModel() {
    private val _errorMessage =  MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    private val _dataUser =  MutableLiveData<AuthResult>()
    val dataUser: LiveData<AuthResult> = _dataUser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    fun register(name: String, email: String, password: String){
        viewModelScope.launch {
            _isLoading.value = true
            val response = repository.register(
                SignUpUser(
                    name, email, password
                )
            )
            when (response) {
                is Response.IsSuccessful<*> -> {
                    _dataUser.value = response.data as AuthResult
                }
                is Response.IsError -> {
                    _errorMessage.value = response.message
                }
            }
            _isLoading.value = false
        }
    }}