package com.fshou.core.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetPhotoResponse(

	@SerialName("color")
	val color: String? = null,

	@SerialName("created_at")
	val createdAt: String? = null,

	@SerialName("description")
	val description: String,

	@SerialName("liked_by_user")
	val likedByUser: Boolean? = null,

	@SerialName("public_domain")
	val publicDomain: Boolean? = null,

	@SerialName("urls")
	val urls: Urls,

	@SerialName("updated_at")
	val updatedAt: String? = null,

	@SerialName("downloads")
	val downloads: Int? = null,

	@SerialName("width")
	val width: Int? = null,

	@SerialName("blur_hash")
	val blurHash: String? = null,

	@SerialName("location")
	val location: Location? = null,

	@SerialName("id")
	val id: String,

	@SerialName("user")
	val user: User? = null,

	@SerialName("height")
	val height: Int? = null,

	@SerialName("likes")
	val likes: Int? = null,

	@SerialName("exif")
	val exif: Exif? = null
)

@Serializable
data class Urls(

	@SerialName("small")
	val small: String,

	@SerialName("thumb")
	val thumb: String,

	@SerialName("raw")
	val raw: String,

	@SerialName("regular")
	val regular: String,

	@SerialName("full")
	val full: String
)

@Serializable
data class User(
	@SerialName("profile_image")
	val profileImage: ProfileImage? = null,

	@SerialName("updated_at")
	val updatedAt: String? = null,

	@SerialName("total_photos")
	val totalPhotos: Int? = null,

	@SerialName("name")
	val name: String? = null,

	@SerialName("bio")
	val bio: String? = null,

	@SerialName("location")
	val location: String? = null,

	@SerialName("total_collections")
	val totalCollections: Int? = null,

	@SerialName("links")
	val links: Links? = null,

	@SerialName("id")
	val id: String? = null,

	@SerialName("total_likes")
	val totalLikes: Int? = null,

	@SerialName("portfolio_url")
	val portfolioUrl: String? = null,

	@SerialName("username")
	val username: String? = null
)

@Serializable
data class Exif(

	@SerialName("exposure_time")
	val exposureTime: String? = null,

	@SerialName("aperture")
	val aperture: String? = null,

	@SerialName("focal_length")
	val focalLength: String? = null,

	@SerialName("iso")
	val iso: Int? = null,

	@SerialName("name")
	val name: String? = null,

	@SerialName("model")
	val model: String? = null,

	@SerialName("make")
	val make: String? = null
)

@Serializable
data class Links(

	@SerialName("portfolio")
	val portfolio: String? = null,

	@SerialName("self")
	val self: String? = null,

	@SerialName("html")
	val html: String? = null,

	@SerialName("photos")
	val photos: String? = null,

	@SerialName("likes")
	val likes: String? = null
)
@Serializable
data class Location(

	@SerialName("country")
	val country: String? = null,

	@SerialName("city")
	val city: String? = null,

	@SerialName("position")
	val position: Position? = null
)

@Serializable
data class Position(

	@SerialName("latitude")
	val latitude: Double? = null,

	@SerialName("longitude")
	val longitude: Double? = null
)
