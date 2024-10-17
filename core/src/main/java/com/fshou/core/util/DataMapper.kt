package com.fshou.core.util

import com.fshou.core.data.remote.response.PhotoDetailResponse
import com.fshou.core.data.remote.response.SearchPhotoResponse
import com.fshou.core.domain.model.Photo

fun SearchPhotoResponse.toPhoto(): Photo = Photo(
    id = this.id,
    description = this.description ?: this.altDescription ?: "",
    urlRegular = this.urls.regular,
    updatedAt = this.updatedAt,
    username = this.user.name,
    userProfileImageUrl = this.user.profileImage.medium
)

fun PhotoDetailResponse.toPhoto(): Photo = Photo(
    id = this.id,
    description = this.description ?: this.altDescription ?: "",
    width = this.width,
    height = this.height,
    country = this.location?.country,
    city = this.location?.city,
    urlRegular = this.urls.regular,
    updatedAt = this.updatedAt,
    username = this.user.name,
    userProfileImageUrl = this.user.profileImage.medium
)