package com.fshou.core.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("profile_image")
    val profileImage: ProfileImage,

    @SerialName("updated_at")
    val updatedAt: String? = null,

    @SerialName("name")
    val name: String,

    @SerialName("bio")
    val bio: String? = null,

    @SerialName("location")
    val location: String? = null,
)