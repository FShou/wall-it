package com.fshou.core.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PhotoDetailResponse(
    @SerialName("id")
    val id: String,

    @SerialName("description")
    val description: String? = null,

    @SerialName("alt_description")
    val altDescription: String? = null,

    @SerialName("urls")
    val urls: Urls,

    @SerialName("updated_at")
    val updatedAt: String,

    @SerialName("width")
    val width: Int? = null,

    @SerialName("location")
    val location: Location? = null,

    @SerialName("user")
    val user: User,

    @SerialName("height")
    val height: Int? = null,

    @SerialName("exif")
    val exif: Exif? = null
)


@Serializable
data class Exif(

    @SerialName("exposure_time")
    val exposureTime: String? = null,

    @SerialName("aperture")
    val aperture: String? = null,

    @SerialName("focal_length")
    val focalLength: String? = null,

    @SerialName("iso")
    val iso: Int? = null,

    @SerialName("name")
    val name: String? = null,

    @SerialName("model")
    val model: String? = null,

    @SerialName("make")
    val make: String? = null
)

@Serializable
data class Location(

    @SerialName("country")
    val country: String? = null,

    @SerialName("city")
    val city: String? = null,

    @SerialName("position")
    val position: Position? = null
)

@Serializable
data class Position(

    @SerialName("latitude")
    val latitude: Double? = null,

    @SerialName("longitude")
    val longitude: Double? = null
)
