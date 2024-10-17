package com.fshou.core.domain.repository

import com.fshou.core.data.remote.response.SearchResponse
import com.fshou.core.domain.model.Photo
import com.fshou.core.util.ColorFilter
import com.fshou.core.util.SortFilter
import kotlinx.coroutines.flow.Flow

interface IPhotoRepository {

    // TODO: Change return type to be The API RESPONSE
    fun getPhotoDetail(id: String) : Flow<Photo>

    fun searchPhotos(term: String, colorFilter: ColorFilter, sortFilter: SortFilter) : Flow<List<Photo>>

    fun bookmarkPhotos(id: String)

}