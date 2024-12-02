package com.fshou.wallit.wallpaper

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.fshou.core.domain.model.Photo
import com.fshou.core.domain.usecase.GetPhotoDetailUseCase

class WallpaperViewModel(
    private val getPhotoDetailUseCase: GetPhotoDetailUseCase
):ViewModel() {

    private var _photo = MutableLiveData<Photo?>(null)
    val photo: LiveData<Photo?> get() = _photo

    private val _downloadedPhotoPath = MutableLiveData<String?>(null)
    val downloadedPhotoPath: LiveData<String?> get() = _downloadedPhotoPath

    fun setDownloadedPath(path: String){
        _downloadedPhotoPath.value = path
    }

    fun setPhoto(photo: Photo) {
        _photo.value = photo
    }
    fun getPhotoDetail(id: String) =
        getPhotoDetailUseCase(id).asLiveData()

}