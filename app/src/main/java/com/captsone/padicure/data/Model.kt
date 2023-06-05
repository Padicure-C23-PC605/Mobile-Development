package com.captsone.padicure.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

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
    val uid: String,
    val name: String,
    val email: String,
    val photoUrl: String
)

@Parcelize
data class ItemData(
    val photoUrl: String,
    val name: String,
    val description: String,
    val detail: String,
    val tutorial: String
): Parcelable

data class ListItemData(
    val list: List<ItemData>
)

