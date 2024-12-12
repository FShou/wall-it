package com.fshou.wallit.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.fshou.core.domain.model.Photo
import com.fshou.core.domain.usecase.CheckBookmarkedPhotoUseCase
import com.fshou.core.domain.usecase.GetPhotoDetailUseCase
import com.fshou.core.domain.usecase.ToggleBookmarkPhotoUseCase
import kotlinx.coroutines.launch

class DetailViewModel(
    private val getPhotoDetailUseCase: GetPhotoDetailUseCase,
    private val toggleBookmarkPhotoUseCase: ToggleBookmarkPhotoUseCase,
    private val checkBookmarkedPhotoUseCase: CheckBookmarkedPhotoUseCase
) : ViewModel() {

    private var _photo = MutableLiveData<Photo?>(null)
    val photo: LiveData<Photo?> get() = _photo

    val isPhotoBookmarked = photo.switchMap {
        it?.let { photo -> checkBookmarkedPhotoUseCase(photo.id).asLiveData() }
    }

    fun setPhoto(photo: Photo) {
        _photo.value = photo
    }

    fun toggleBookmark() {
        viewModelScope.launch {
            photo.value?.let { toggleBookmarkPhotoUseCase(it) }
        }
        _photo.value = _photo.value?.copy() // Just for triggering change
    }


    fun getPhotoDetail(photoId: String) = getPhotoDetailUseCase(photoId).asLiveData()
}