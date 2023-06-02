package com.captsone.padicure.data


sealed class Response{
    data class IsError(
        var error: Boolean,
        var message: String): Response()
    data class IsSuccessful<T>(
        var data: T? = null
    ): Response()
}