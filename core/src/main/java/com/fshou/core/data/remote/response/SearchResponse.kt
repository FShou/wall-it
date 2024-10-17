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
	val results: List<PhotoEntity>
) 



@Serializable
data class PhotoEntity(

	@SerialName("id")
	val id: String,

	@SerialName("description")
	val description: String,

	@SerialName("urls")
	val urls: Urls,

	@SerialName("user")
	val user: User,
)


@Serializable
data class ProfileImage(

	@SerialName("small")
	val small: String? = null,

)

