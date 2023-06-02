package com.captsone.padicure

object Utils {
    fun extractErrorMessage(message: String): String {
        val startIndex = message.indexOf(":") + 2
        val endIndex = message.indexOf("[") - 1
//        return message.substring(startIndex, endIndex)
        return message
    }

}