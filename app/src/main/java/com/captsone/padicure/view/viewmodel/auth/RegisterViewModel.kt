package com.captsone.padicure.view.viewmodel.auth

import androidx.lifecycle.ViewModel
import com.captsone.padicure.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: Repository
)
    : ViewModel() {
    // TODO: Implement the ViewModel
}