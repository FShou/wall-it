package com.fshou.core.data.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchResponse(

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("total_pages")
	val totalPages: Int? = null,

	@field:SerializedName("results")
	val results: List<Photo?>? = null
) : Parcelable



@Parcelize
data class Photo(

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("urls")
	val urls: Urls? = null,

	@field:SerializedName("user")
	val user: User? = null,
) : Parcelable

@Parcelize
data class User(

	@field:SerializedName("profile_image")
	val profileImage: ProfileImage? = null,

	@field:SerializedName("name")
	val name: String? = null,

) : Parcelable

@Parcelize
data class ProfileImage(

	@field:SerializedName("small")
	val small: String? = null,

) : Parcelable

@Parcelize
data class Urls(

	@field:SerializedName("small")
	val small: String? = null,
//
//	@field:SerializedName("regular")
//	val regular: String? = null,

) : Parcelable
