package com.fshou.wallit.discover

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.fshou.core.domain.model.Photo
import com.fshou.core.domain.usecase.SearchPhotosUseCase
import com.fshou.core.util.ColorFilter
import com.fshou.core.util.FetchState
import com.fshou.core.util.SortFilter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn

class DiscoverViewModel(private val searchPhotosUseCase: SearchPhotosUseCase) : ViewModel() {

    val listSearchedPhoto = flow{
        emitAll(searchPhotosUseCase(term = "minimal", null, null))
    }.asLiveData()
}