package com.fshou.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fshou.core.data.local.entity.PhotoEntity

@Database(entities = [PhotoEntity::class], version = 1, exportSchema = false)
abstract class PhotoDatabase: RoomDatabase() {
    abstract fun photoDao(): PhotoDao
}