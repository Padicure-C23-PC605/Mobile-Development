package com.captsone.padicure.view

interface Screen {
    fun showLoading(isLoading: Boolean)
    fun showMessage(message: String)
}