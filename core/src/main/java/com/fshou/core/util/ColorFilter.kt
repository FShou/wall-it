package com.fshou.core.util

enum class ColorFilter {
    BLACK_AND_WHITE,
    BLACK,
    WHITE,
    YELLOW,
    ORANGE,
    RED,
    PURPLE,
    MAGENTA,
    GREEN,
    TEAL,
    BLUE
}

fun getColor(colorFilter: ColorFilter): String = when(colorFilter) {
    ColorFilter.BLACK_AND_WHITE -> "black_and_white"
    ColorFilter.BLACK -> "black"
    ColorFilter.WHITE -> "white"
    ColorFilter.YELLOW -> "yellow"
    ColorFilter.ORANGE -> "orange"
    ColorFilter.RED -> "red"
    ColorFilter.PURPLE -> "purple"
    ColorFilter.MAGENTA -> "magenta"
    ColorFilter.GREEN -> "green"
    ColorFilter.TEAL -> "teal"
    ColorFilter.BLUE -> "blue"
}