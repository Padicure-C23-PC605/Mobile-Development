package com.captsone.padicure.view.viewmodel

import android.content.Context
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.captsone.padicure.data.Repository
import com.captsone.padicure.data.Response
import com.captsone.padicure.data.ScannedData
import com.captsone.padicure.utils.Utils.reduceFileImage
import com.captsone.padicure.utils.Utils.uriToFile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ScanViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {

    private val _file = MutableLiveData<File>()
    val file: LiveData<File> = _file

    private val _isEmpty = MutableLiveData<Boolean>()
    val isEmpty: LiveData<Boolean> = _isEmpty

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    private val _data = MutableLiveData<ScannedData>()
    val data: LiveData<ScannedData> = _data

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    init {
        _isEmpty.value = true
    }

    fun setFile(uri: Uri, context: Context) {
        _file.value = reduceFileImage(uriToFile(uri, context))
        scanData()
    }

    fun setFileFromCamera(file: File) {
        _file.value = file
        scanData()
    }

    private fun scanData(){
        try{
            val requestImageFile = _file.value!!.asRequestBody("image/jpeg".toMediaType())
            val imageMultiPart: MultipartBody.Part = MultipartBody.Part.createFormData(
                "file",
                _file.value!!.name,
                requestImageFile
            )
            this.viewModelScope.launch {
                _isLoading.value = true
                when (val response = repository.scanData(imageMultiPart)){
                is Response.IsSuccessful<*> ->{
                    _data.value = response.data as ScannedData
                    _isEmpty.value = false

                }
                is Response.IsError -> {
                    _message.value = response.message
                    _isEmpty.value = true
                }
            }
                _isLoading.value = false
            }
        } catch (e: Exception){
            _message.value = e.toString()
        }
    }
}