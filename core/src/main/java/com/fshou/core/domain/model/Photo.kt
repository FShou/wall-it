package com.fshou.core.domain.model


data class Photo(
    val id: String,
    val description: String,
    val country: String? = null,
    val city: String? = null,
    val urlRegular: String,
    // TODO: Url Thumbnail instead for List Photo
    val urlThumb: String,
    val updatedAt: String,
    val username: String,
    val userProfileImageUrl: String,
    val width: Int,
    val height: Int,
    val isBookmarked: Boolean = false
)