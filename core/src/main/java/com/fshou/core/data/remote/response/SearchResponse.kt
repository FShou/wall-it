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
	val results: List<Photo>? = null
) 



@Serializable
data class Photo(

	@SerialName("id")
	val id: String? = null,

	@SerialName("description")
	val description: String? = null,

	@SerialName("urls")
	val urls: Urls? = null,

	@SerialName("user")
	val user: User? = null,
)

@Serializable
data class ProfileImage(

	@SerialName("small")
	val small: String? = null,

)

