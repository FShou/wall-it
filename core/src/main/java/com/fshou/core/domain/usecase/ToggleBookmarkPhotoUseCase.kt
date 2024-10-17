package com.fshou.core.domain.usecase

import com.fshou.core.domain.model.Photo

fun interface ToggleBookmarkPhotoUseCase {
    operator fun invoke(photo: Photo)
}