package com.fshou.wallit.wallpaper

import com.fshou.wallit.wallpaper.WallpaperViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val wallpaperModule = module {
    viewModel { WallpaperViewModel(get()) }
}