package com.fshou.core.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Urls(

    @SerialName("small")
    val small: String,

    @SerialName("thumb")
    val thumb: String,

    @SerialName("raw")
    val raw: String,

    @SerialName("regular")
    val regular: String,

    @SerialName("full")
    val full: String
)