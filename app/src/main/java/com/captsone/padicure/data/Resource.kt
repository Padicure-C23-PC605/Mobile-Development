package com.captsone.padicure.data


sealed class Response{
    data class IsError<T>(
        var data: T? = null,
        var error: Boolean,
        var message: String): Response()
}