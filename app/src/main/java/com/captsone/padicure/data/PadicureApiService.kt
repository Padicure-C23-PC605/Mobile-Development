package com.captsone.padicure.data

import retrofit2.http.GET
import retrofit2.http.Path

interface PadicureApiService {
    @GET("/homepage")
    suspend fun getListData(): ListItemData

    @GET("/detail/{id}")
    suspend fun getDetailData(@Path("id") id: Int): ItemData
}