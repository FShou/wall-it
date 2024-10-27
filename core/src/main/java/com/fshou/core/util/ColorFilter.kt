package com.fshou.core.util

import android.view.View
import com.fshou.core.R
import com.fshou.core.util.ColorFilter.BLACK
import com.fshou.core.util.ColorFilter.BLACK_AND_WHITE
import com.fshou.core.util.ColorFilter.BLUE
import com.fshou.core.util.ColorFilter.GREEN
import com.fshou.core.util.ColorFilter.MAGENTA
import com.fshou.core.util.ColorFilter.ORANGE
import com.fshou.core.util.ColorFilter.PURPLE
import com.fshou.core.util.ColorFilter.RED
import com.fshou.core.util.ColorFilter.TEAL
import com.fshou.core.util.ColorFilter.WHITE
import com.fshou.core.util.ColorFilter.YELLOW
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

enum class ColorFilter(val iconResId: Int, val displayName: String) {
    BLACK_AND_WHITE(R.drawable.bg_black_and_white, "Black & White"),
    BLACK(R.drawable.bg_black, "Black"),
    WHITE(R.drawable.bg_white, "White"),
    YELLOW(R.drawable.bg_yellow, "Yellow"),
    ORANGE(R.drawable.bg_orange, "Orange"),
    RED(R.drawable.bg_red, "Red"),
    PURPLE(R.drawable.bg_purple, "Purple"),
    MAGENTA(R.drawable.bg_magenta, "Magenta"),
    GREEN(R.drawable.bg_green, "Green"),
    TEAL(R.drawable.bg_teal, "Teal"),
    BLUE(R.drawable.bg_blue, "Blue");

    companion object {
        val chipIdToColorFilterMap = mutableMapOf<Int, ColorFilter>()

        fun getColorFilterFromChipId(id: Int, map: Map<Int, ColorFilter>): ColorFilter? = map[id]
        fun getColor(colorFilter: ColorFilter): String = when(colorFilter) {
            BLACK_AND_WHITE -> "black_and_white"
            BLACK -> "black"
            WHITE -> "white"
            YELLOW -> "yellow"
            ORANGE -> "orange"
            RED -> "red"
            PURPLE -> "purple"
            MAGENTA -> "magenta"
            GREEN -> "green"
            TEAL -> "teal"
            BLUE -> "blue"
        }

        fun ChipGroup.addColorFilterChips(onChipClick: (ColorFilter) -> Unit) {

            ColorFilter.entries.forEach { colorFilter ->
                val chip = Chip(context).apply {
                    id = View.generateViewId()
                    chipIdToColorFilterMap[id] = colorFilter

                    layoutParams = ChipGroup.LayoutParams(
                        ChipGroup.LayoutParams.WRAP_CONTENT,
                        ChipGroup.LayoutParams.WRAP_CONTENT
                    )
                    isChecked = false
                    isCheckable = true
                    text = colorFilter.displayName
                    setChipIconResource(colorFilter.iconResId)
                    setOnClickListener {
                        onChipClick(colorFilter)
                    }
                }
                addView(chip)
            }
        }
    }
}



