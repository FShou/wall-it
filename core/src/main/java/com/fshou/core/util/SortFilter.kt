package com.fshou.core.util

enum class SortFilter {
    RELEVANT,
    LATEST
}

fun getSort(sortFilter: SortFilter): String = when(sortFilter){
    SortFilter.RELEVANT -> "relevant"
    SortFilter.LATEST -> "latest"
}