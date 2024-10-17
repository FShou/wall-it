package com.fshou.core.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileImage(

    @SerialName("small")
    val small: String? = null,

    @SerialName("medium")
    val medium: String,

    @SerialName("large")
    val large: String? = null,
)