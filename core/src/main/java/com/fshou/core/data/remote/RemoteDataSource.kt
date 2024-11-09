package com.fshou.core.data.remote

import android.util.Log
import com.fshou.core.data.remote.api.UnsplashApi
import com.fshou.core.data.remote.response.PhotoDetailResponse
import com.fshou.core.data.remote.response.SearchPhotoResponse
import com.fshou.core.util.FetchState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val unsplashApi: UnsplashApi) {
    fun searchPhotos(
        term: String, color: String?, sort: String?
    ) = flow<FetchState<List<SearchPhotoResponse>>> {
        emit(FetchState.Loading())
        try {
            val response = unsplashApi.searchPhotos(term, color, sort)
            val data = response.results
            emit(FetchState.Success(data))
        } catch (e: Exception) {
            emit(FetchState.Error(e.message.toString()))
            Log.e("RemoteDataSource", e.toString())
        }
    }.flowOn(Dispatchers.IO)

    fun getPhotoDetail(id: String) = flow<FetchState<PhotoDetailResponse>> {
        emit(FetchState.Loading())
        try {
            val response = unsplashApi.getPhotoById(id)

            emit(FetchState.Success(response))

        } catch (e: Exception) {
            emit(FetchState.Error(e.message.toString()))
            Log.e("RemoteDataSource", e.toString())
        }
    }.flowOn(Dispatchers.IO)
}