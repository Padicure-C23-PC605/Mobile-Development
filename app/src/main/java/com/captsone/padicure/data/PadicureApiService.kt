package com.captsone.padicure.data

import okhttp3.MultipartBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Url

interface PadicureApiService {
    @GET("/homepage")
    suspend fun getListData(): ListItemData

    @GET("/detail/{id}")
    suspend fun getDetailData(@Path("id") id: Int): ItemData

    @POST
    @Multipart
    suspend fun predictPicture(
        @Url url: String,
        @Part file: MultipartBody.Part
    ): ScannedData
}