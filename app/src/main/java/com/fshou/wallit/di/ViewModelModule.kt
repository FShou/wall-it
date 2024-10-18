package com.fshou.wallit.di

import com.fshou.wallit.bookmark.BookmarkViewModel
import com.fshou.wallit.detail.DetailViewModel
import com.fshou.wallit.discover.DiscoverViewModel
import com.fshou.wallit.home.HomeViewModel
import com.fshou.wallit.onboarding.OnBoardingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel()  }
    viewModel { BookmarkViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { OnBoardingViewModel() }
    viewModel { DiscoverViewModel(get()) }
}