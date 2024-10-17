package com.fshou.core.util

import com.fshou.core.data.remote.response.GetPhotoResponse
import com.fshou.core.data.remote.response.PhotoEntity
import com.fshou.core.domain.model.Photo

fun PhotoEntity.toPhoto(): Photo = Photo(
    id = this.id,
    description = this.description,
    displayUrl = this.urls.small,
    downloadUrl = this.urls.full,
)

fun GetPhotoResponse.toPhoto(): Photo = Photo(
    id = this.id,
    description = this.description,
    displayUrl = this.urls.full,
    downloadUrl = this.urls.raw,
    updatedAt = this.updatedAt
)