package com.fshou.core.domain.usecase

import kotlinx.coroutines.flow.Flow

fun interface CheckBookmarkedPhotoUseCase {
    operator fun invoke(photoId: String): Flow<Boolean>
}