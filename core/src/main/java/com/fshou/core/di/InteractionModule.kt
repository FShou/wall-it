package com.fshou.core.di

import com.fshou.core.domain.repository.IPhotoRepository
import com.fshou.core.domain.usecase.GetBookmarkedPhotosUseCase
import com.fshou.core.domain.usecase.GetPhotoDetailUseCase
import com.fshou.core.domain.usecase.SearchPhotosUseCase
import com.fshou.core.domain.usecase.ToggleBookmarkPhotoUseCase
import org.koin.dsl.module

val interactionModule = module {

    single { SearchPhotosUseCase(get<IPhotoRepository>()::searchPhotos) }

    single { GetPhotoDetailUseCase(get<IPhotoRepository>()::getPhotoDetail) }

    single { GetBookmarkedPhotosUseCase(get<IPhotoRepository>()::getBookmarkedPhotos) }

    single { ToggleBookmarkPhotoUseCase(get<IPhotoRepository>()::toggleBookmarkPhoto) }
}