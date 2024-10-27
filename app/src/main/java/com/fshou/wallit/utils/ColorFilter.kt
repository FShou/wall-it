package com.fshou.wallit.utils

import android.view.View
import com.fshou.core.util.ColorFilter
import com.fshou.core.util.ColorFilter.Companion.chipIdToColorFilterMap
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

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