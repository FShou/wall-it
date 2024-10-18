package com.fshou.core.domain.usecase

import com.fshou.core.domain.model.Photo
import com.fshou.core.util.ColorFilter
import com.fshou.core.util.FetchState
import com.fshou.core.util.SortFilter
import kotlinx.coroutines.flow.Flow

typealias SearchPhoto = suspend (String, ColorFilter, SortFilter) -> Flow<FetchState<List<Photo>>>
typealias GetBookmarkedPhotos =  () -> Flow<List<Photo>>
typealias GetPhotoDetail = suspend (String) -> Flow<FetchState<Photo>>
typealias ToogleBookmarkPhoto = suspend (Photo) -> Unit
