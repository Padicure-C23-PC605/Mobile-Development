package com.captsone.padicure.utils

import com.captsone.padicure.data.ItemData

object Utils {
    fun extractErrorMessage(message: String): String {
        val startIndex = message.indexOf(":") + 2
        val endIndex = message.indexOf("[") - 1
//        return message.substring(startIndex, endIndex)
        return message
    }

//    val dummyData = listOf(
//        ItemData("https://picsum.photos/200/300", "Leafblast", "Jamur Pyricularia oryzae pada daun padi.",
//        "LoremIpsum",""),
//        ItemData("https://picsum.photos/200/300", "Leafblast", "Jamur Pyricularia oryzae pada daun padi.",
//            "LoremIpsum",""),
//        ItemData("https://picsum.photos/200/300", "Leafblast", "Jamur Pyricularia oryzae pada daun padi.",
//            "LoremIpsum",""),
//        ItemData("https://picsum.photos/200/300", "Leafblast", "Jamur Pyricularia oryzae pada daun padi.",
//            "LoremIpsum",""),
//    )

}