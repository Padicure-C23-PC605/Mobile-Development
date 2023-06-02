package com.captsone.padicure.data

interface RepositoryResource {
    suspend fun login(user: SignInUser): Response
    suspend fun register(user: SignUpUser): Response
    suspend fun isLogin(): Response
    suspend fun test()
}