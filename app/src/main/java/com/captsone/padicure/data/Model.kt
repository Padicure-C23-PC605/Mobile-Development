package com.captsone.padicure.data

data class SignInUser(
    val email: String,
    val password: String
)

data class SignUpUser(
    val name: String,
    val email: String,
    val password: String
)

data class UserData(
    val name: String,
    val email: String,
    val photoUrl: String
)


