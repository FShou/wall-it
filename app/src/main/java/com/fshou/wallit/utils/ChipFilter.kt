package com.fshou.wallit.utils

import android.view.View
import com.fshou.core.util.ColorFilter
import com.fshou.core.util.ColorFilter.Companion.chipIdToColorFilterMap
import com.fshou.core.util.SortFilter
import com.fshou.core.util.getSort
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import java.util.Locale

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

fun ChipGroup.addSortFilterChips(onChipClick: (SortFilter) -> Unit) {
    SortFilter.entries.forEach { sortFilter ->
        val chip = Chip(context).apply {
            layoutParams = ChipGroup.LayoutParams(
                ChipGroup.LayoutParams.WRAP_CONTENT,
                ChipGroup.LayoutParams.WRAP_CONTENT
            )
            isChecked = false
            isCheckable = true
            text = getSort(sortFilter).replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.getDefault()
                ) else it.toString()
            }
            setOnClickListener { onChipClick(sortFilter) }
        }
        addView(chip)
    }
}