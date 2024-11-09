package com.fshou.wallit.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.fshou.core.domain.usecase.GetBookmarkedPhotosUseCase

class BookmarkViewModel(private val getBookmarkedPhotosUseCase: GetBookmarkedPhotosUseCase) : ViewModel() {
    val listBookmarkedPhoto = getBookmarkedPhotosUseCase().asLiveData()

}