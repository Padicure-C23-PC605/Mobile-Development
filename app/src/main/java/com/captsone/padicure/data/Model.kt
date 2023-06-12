package com.captsone.padicure.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
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
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val photoUrl: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("detail")
    val detail: String,
    @SerializedName("howtocure")
    val tutorial: String
) : Parcelable

data class ListItemData(
    @SerializedName("response")
    val list: List<ItemData>
)

