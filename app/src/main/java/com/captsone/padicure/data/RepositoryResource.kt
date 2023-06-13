package com.captsone.padicure.data

import okhttp3.MultipartBody
import retrofit2.http.Part

interface RepositoryResource {
    suspend fun login(user: SignInUser): Response
    suspend fun register(user: SignUpUser): Response
    suspend fun isLogin(): Response
    suspend fun test()
    suspend fun getUserData(): Response
    suspend fun setUserData(name: String, profilePictURL: String): Response
    suspend fun logout(): Response
    suspend fun getListData(): Response
    suspend fun getDetailData(id: Int): Response
    suspend fun scanData(
        @Part photo: MultipartBody.Part
    ): Response
}