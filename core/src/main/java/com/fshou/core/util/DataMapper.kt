package com.fshou.core.util

import com.fshou.core.data.local.entity.PhotoEntity
import com.fshou.core.data.remote.response.PhotoDetailResponse
import com.fshou.core.data.remote.response.SearchPhotoResponse
import com.fshou.core.domain.model.Photo

fun SearchPhotoResponse.toPhoto(): Photo = Photo(
    id = this.id,
    description = this.description ?: this.altDescription ?: "",
    width = this.width,
    height = this.height,
    urlRegular = this.urls.regular,
    urlThumb = this.urls.thumb,
    updatedAt = this.updatedAt,
    username = this.user.name,
    color = this.color,
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
    urlThumb = this.urls.thumb,
    updatedAt = this.updatedAt,
    username = this.user.name,
    color = this.color,
    userProfileImageUrl = this.user.profileImage.medium
)

fun Photo.toPhotoEntity() = PhotoEntity(
    id,
    description,
    country,
    city,
    urlRegular,
    urlThumb,
    updatedAt,
    username,
    color,
    userProfileImageUrl,
    width,
    height,
    isBookmarked
)

fun PhotoEntity.toPhoto() = Photo(
    id,
    description,
    country,
    city,
    urlRegular,
    urlThumb,
    updatedAt,
    username,
    color,
    userProfileImageUrl,
    width,
    height,
    isBookmarked
)