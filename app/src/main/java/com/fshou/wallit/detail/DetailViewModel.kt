package com.fshou.wallit.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.fshou.core.domain.model.Photo
import com.fshou.core.domain.usecase.GetPhotoDetailUseCase
import com.fshou.core.util.FetchState

class DetailViewModel(private val getPhotoDetailUseCase: GetPhotoDetailUseCase) : ViewModel() {
    private var _photo = MutableLiveData<Photo?>(null)
    val photo: LiveData<Photo?> get() = _photo

    fun setPhoto(photo: Photo) {
        _photo.value = photo
    }


    fun getPhotoDetail(photoId: String) = getPhotoDetailUseCase(photoId).asLiveData()
}