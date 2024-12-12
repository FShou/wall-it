package com.fshou.wallit.wallpaper

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val wallpaperModule = module {
    viewModel { WallpaperViewModel(get()) }
}