package com.fshou.core.data.remote.api

import com.fshou.core.data.remote.response.GetPhotoResponse
import com.fshou.core.data.remote.response.SearchResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.appendPathSegments
import io.ktor.http.path

class UnsplashApi(private val httpClient: HttpClient) {

    suspend fun searchPhotos(
        term: String,
        colorFilter: String? = null,
        sortFilter: String? = null,
    ): SearchResponse {
        return httpClient.get{
            url {
                path("search/photos")
                parameters.append("query", term)
                if (colorFilter != null) {
                    parameters.append("color", colorFilter)
                }
                if (sortFilter != null) {
                    parameters.append("order_by", sortFilter)
                }
            }
        }.body()
    }

     suspend fun getPhotoById(id: String) : GetPhotoResponse {
        return httpClient.get {
            url {
                path("photos/${id}")
            }
        }.body()
    }
}