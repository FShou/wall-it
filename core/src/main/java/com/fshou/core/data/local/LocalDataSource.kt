package com.fshou.core.data.local

import com.fshou.core.data.local.entity.PhotoEntity
import com.fshou.core.data.local.room.PhotoDao

class LocalDataSource(
    private val photoDao: PhotoDao
) {

    fun getAllPhotos() = photoDao.getAllPhotos()

     suspend fun insertPhoto(photo: PhotoEntity) = photoDao.insertPhoto(photo)

    suspend fun deletePhoto(photo: PhotoEntity) = photoDao.deletePhoto(photo)

    fun checkIsBookmarked(photoId: String) = photoDao.checkIsBookmarked(photoId)
}