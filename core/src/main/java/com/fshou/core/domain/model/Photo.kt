package com.fshou.core.domain.model

data class Photo(
    val id: String,
    val description: String,
    val displayUrl: String,
    val downloadUrl: String,
    val updatedAt: String? = null,
    val location : Location? = null
)