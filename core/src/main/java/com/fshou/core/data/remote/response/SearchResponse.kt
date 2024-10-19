package com.fshou.core.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResponse(

	@SerialName("total")
	val total: Int? = null,

	@SerialName("total_pages")
	val totalPages: Int? = null,

	@SerialName("results")
	val results: List<SearchPhotoResponse>
) 



@Serializable
data class SearchPhotoResponse(

	@SerialName("id")
	val id: String,

	@SerialName("description")
	val description: String? = null,

	@SerialName("alt_description")
	val altDescription: String? = null,

	@SerialName("width")
	val width: Int,

	@SerialName("height")
	val height: Int,

	@SerialName("updated_at")
	val updatedAt: String,

	@SerialName("urls")
	val urls: Urls,

	@SerialName("user")
	val user: User,


)




