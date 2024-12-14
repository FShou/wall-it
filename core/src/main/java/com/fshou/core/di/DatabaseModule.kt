package com.fshou.core.di

import androidx.room.Room
import com.fshou.core.data.local.room.PhotoDao
import com.fshou.core.data.local.room.PhotoDatabase
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single<PhotoDao> {
        get<PhotoDatabase>().photoDao()
    }

    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("WallIt".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(), PhotoDatabase::class.java, "photo.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }

}