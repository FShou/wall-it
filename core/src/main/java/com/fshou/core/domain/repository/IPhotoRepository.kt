package com.fshou.core.domain.repository

import com.fshou.core.data.remote.response.SearchResponse
import com.fshou.core.domain.model.Photo
import com.fshou.core.util.ColorFilter
import com.fshou.core.util.FetchState
import com.fshou.core.util.SortFilter
import kotlinx.coroutines.flow.Flow

interface IPhotoRepository {

    fun getPhotoDetail(id: String) : Flow<FetchState<Photo>>

    fun searchPhotos(term: String, colorFilter: ColorFilter, sortFilter: SortFilter) : Flow<FetchState<List<Photo>>>

    fun bookmarkPhotos(id: String)

}