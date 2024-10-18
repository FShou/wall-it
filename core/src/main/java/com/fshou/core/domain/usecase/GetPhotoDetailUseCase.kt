package com.fshou.core.domain.usecase

import com.fshou.core.domain.model.Photo
import com.fshou.core.util.FetchState
import kotlinx.coroutines.flow.Flow

fun interface GetPhotoDetailUseCase {
    suspend operator fun invoke(id: String): Flow<FetchState<Photo>>
}