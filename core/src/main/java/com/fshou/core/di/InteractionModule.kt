package com.fshou.core.di

import com.fshou.core.domain.model.Photo
import com.fshou.core.domain.repository.IPhotoRepository
import com.fshou.core.domain.usecase.GetBookmarkedPhotos
import com.fshou.core.domain.usecase.GetBookmarkedPhotosUseCase
import com.fshou.core.domain.usecase.GetPhotoDetail
import com.fshou.core.domain.usecase.GetPhotoDetailUseCase
import com.fshou.core.domain.usecase.SearchPhoto
import com.fshou.core.domain.usecase.SearchPhotosUseCase
import com.fshou.core.domain.usecase.ToggleBookmarkPhotoUseCase
import com.fshou.core.domain.usecase.ToogleBookmarkPhoto
import com.fshou.core.util.ColorFilter
import com.fshou.core.util.SortFilter
import org.koin.dsl.module


val interactionModule = module {

    // type aliases
    single<SearchPhoto> {
        val searchPhoto: SearchPhoto =  { term: String, color: ColorFilter, sort:SortFilter ->
            get<IPhotoRepository>().searchPhotos(term,color,sort)
        }

        searchPhoto
    }
    single<GetBookmarkedPhotos> { get<IPhotoRepository>()::getBookmarkedPhotos }
    single<GetPhotoDetail> { get<IPhotoRepository>()::getPhotoDetail  }
    single<ToogleBookmarkPhoto> { get<IPhotoRepository>()::toggleBookmarkPhoto  }

    // fun interface
    single { SearchPhotosUseCase(get<IPhotoRepository>()::searchPhotos) }

    single { GetPhotoDetailUseCase(get<IPhotoRepository>()::getPhotoDetail) }

    single { GetBookmarkedPhotosUseCase(get<IPhotoRepository>()::getBookmarkedPhotos) }

    single { ToggleBookmarkPhotoUseCase(get<IPhotoRepository>()::toggleBookmarkPhoto) }
}