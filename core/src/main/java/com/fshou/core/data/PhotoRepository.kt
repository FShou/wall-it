package com.fshou.core.data

import com.fshou.core.data.remote.RemoteDataSource
import com.fshou.core.domain.model.Photo
import com.fshou.core.domain.repository.IPhotoRepository
import com.fshou.core.util.ColorFilter
import com.fshou.core.util.FetchState
import com.fshou.core.util.SortFilter
import com.fshou.core.util.getColor
import com.fshou.core.util.getSort
import com.fshou.core.util.toPhoto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class PhotoRepository(
    private val remoteDataSource: RemoteDataSource
) : IPhotoRepository {
    override suspend fun getPhotoDetail(id: String): Flow<FetchState<Photo>> =
        remoteDataSource.getPhotoDetail(id)
            .map { fetchState ->
                when (fetchState) {
                    is FetchState.Error -> FetchState.Error(fetchState.message.toString())
                    is FetchState.Loading -> FetchState.Loading()
                    is FetchState.Success -> FetchState.Success(fetchState.data!!.toPhoto())
                }
            }.flowOn(Dispatchers.IO)

    override suspend fun searchPhotos(
        term: String,
        colorFilter: ColorFilter?,
        sortFilter: SortFilter?
    ): Flow<FetchState<List<Photo>>> {
        val color = colorFilter?.let { getColor(it) }
        val sort = sortFilter?.let { getSort(it) }

        return remoteDataSource.searchPhotos(term, color, sort)
            .map { fetchState ->
                when (fetchState) {
                    is FetchState.Error -> FetchState.Error(fetchState.message.toString())
                    is FetchState.Loading -> FetchState.Loading()
                    is FetchState.Success -> FetchState.Success(fetchState.data!!.map { it.toPhoto() })
                }
            }.flowOn(Dispatchers.IO)
    }

    override fun bookmarkPhotos(id: String) {
        TODO("Not yet implemented")
    }
}