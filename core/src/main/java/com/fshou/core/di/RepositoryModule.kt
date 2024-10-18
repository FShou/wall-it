package com.fshou.core.di

import com.fshou.core.data.PhotoRepository
import com.fshou.core.data.local.LocalDataSource
import com.fshou.core.data.remote.RemoteDataSource
import com.fshou.core.domain.repository.IPhotoRepository
import org.koin.dsl.module

val repostoryModule = module {

    single<RemoteDataSource> {
        RemoteDataSource(get())
    }

    single<LocalDataSource> {
        LocalDataSource(get())
    }

    single<IPhotoRepository> {
        PhotoRepository(get(),get())
    }
}