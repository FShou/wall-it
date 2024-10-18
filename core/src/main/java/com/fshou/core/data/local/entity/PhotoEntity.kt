package com.fshou.core.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photo")
data class PhotoEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,

    val description: String,
    val country: String? = null,
    val city: String? = null,

    @ColumnInfo(name = "url_regular")
    val urlRegular: String,

    @ColumnInfo(name = "updated_at")
    val updatedAt: String,

    @ColumnInfo(name = "username")
    val username: String,

    @ColumnInfo(name = "user_profile_image_url")
    val userProfileImageUrl: String,

    val width: Int? = null,
    val height: Int? = null,

    @ColumnInfo(name = "is_bookmarked", defaultValue = "false")
    val isBookmarked: Boolean = false
)