package com.fshou.core.domain.usecase

import com.fshou.core.domain.model.Photo
import kotlinx.coroutines.flow.Flow

fun interface GetPhotoDetail {
    operator fun invoke(id: String): Flow<Photo>
}