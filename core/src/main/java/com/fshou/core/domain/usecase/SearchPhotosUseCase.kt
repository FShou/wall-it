package com.fshou.core.domain.usecase

import com.fshou.core.domain.model.Photo
import com.fshou.core.util.ColorFilter
import com.fshou.core.util.FetchState
import com.fshou.core.util.SortFilter
import kotlinx.coroutines.flow.Flow

fun interface SearchPhotosUseCase {
    operator fun invoke(term: String, color: ColorFilter, sortFilter: SortFilter): Flow<FetchState<List<Photo>>>
}