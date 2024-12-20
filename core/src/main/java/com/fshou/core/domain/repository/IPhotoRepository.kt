package com.fshou.core.domain.repository

import com.fshou.core.domain.model.Photo
import com.fshou.core.util.ColorFilter
import com.fshou.core.util.FetchState
import com.fshou.core.util.SortFilter
import kotlinx.coroutines.flow.Flow

interface IPhotoRepository {

    fun getPhotoDetail(id: String): Flow<FetchState<Photo>>

    fun searchPhotos(
        term: String,
        colorFilter: ColorFilter?,
        sortFilter: SortFilter?
    ): Flow<FetchState<List<Photo>>>

    suspend fun toggleBookmarkPhoto(photo: Photo)

    fun checkBookmarkedPhoto(photoId: String): Flow<Boolean>

    fun getBookmarkedPhotos(): Flow<List<Photo>>
}