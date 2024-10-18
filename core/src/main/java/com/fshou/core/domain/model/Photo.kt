package com.fshou.core.domain.model


data class Photo(
    val id: String,
    val description: String,
    val country: String? = null,
    val city: String? = null,
    val urlRegular: String,
    // TODO: Url Thumbnail instead for List Photo
    val updatedAt: String,
    val username: String,
    val userProfileImageUrl: String,
    val width: Int? = null,
    val height: Int? = null,
    val isBookmarked: Boolean = false
)