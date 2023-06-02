package com.captsone.padicure.view

interface Screen {
    fun showLoading(isLoading: Boolean)
    fun showError(message: String)
}