package com.fshou.wallit.discover

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import com.fshou.core.domain.usecase.SearchPhotosUseCase
import com.fshou.core.util.ColorFilter
import com.fshou.core.util.SortFilter


class DiscoverViewModel(private val searchPhotosUseCase: SearchPhotosUseCase) : ViewModel() {


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

    private val _searchTerm = MutableLiveData<String?>(null)
    val searchTerm: LiveData<String?> get() = _searchTerm

    fun setSearchTerm(term: String) {
        _searchTerm.value = term
    }

    val listSearchedPhoto =
        _searchTerm.switchMap { term ->
            _selectedColor.switchMap { color ->
                _selectedSort.switchMap { sort ->
                    searchPhotosUseCase(term ?: "minimal", color, sort).asLiveData()
                }
            }
        }
}