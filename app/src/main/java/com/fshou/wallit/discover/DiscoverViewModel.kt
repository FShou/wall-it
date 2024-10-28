package com.fshou.wallit.discover

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.fshou.core.domain.usecase.SearchPhotosUseCase
import com.fshou.core.util.ColorFilter
import com.fshou.core.util.SortFilter
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow


class DiscoverViewModel(private val searchPhotosUseCase: SearchPhotosUseCase) : ViewModel() {

    val listSearchedPhoto = flow{
        emitAll(searchPhotosUseCase(term = "minimal", null, null))
    }.asLiveData()

    private val _selectedColor = MutableLiveData<ColorFilter?>(null)
    val selectedColor: LiveData<ColorFilter?> get() = _selectedColor

    fun selectColorFilter(colorFilter: ColorFilter?) {
        _selectedColor.value = colorFilter // Set the selected color filter
    }

    private val _selectedSort = MutableLiveData<SortFilter?>(null)
    val selectedSort: LiveData<SortFilter?> get() = _selectedSort

    fun selectSortFilter(sortFilter: SortFilter?) {
        _selectedSort.value = sortFilter // Set the selected color filter
    }
}