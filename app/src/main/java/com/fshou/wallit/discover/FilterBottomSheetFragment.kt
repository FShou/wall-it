package com.fshou.wallit.discover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fshou.core.util.ColorFilter
import com.fshou.core.util.ColorFilter.Companion.chipIdToColorFilterMap
import com.fshou.core.util.ColorFilter.Companion.getColorFilterFromChipId
import com.fshou.core.util.SortFilter
import com.fshou.wallit.databinding.FilterBottomSheetContentBinding
import com.fshou.wallit.utils.addColorFilterChips
import com.fshou.wallit.utils.addSortFilterChips
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class FilterBottomSheetFragment : BottomSheetDialogFragment() {

    private var _binding: FilterBottomSheetContentBinding? = null
    private val binding get() = _binding!!

    private val viewModel by activityViewModel<DiscoverViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FilterBottomSheetContentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.setUpView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Clean up binding reference
    }

    private fun FilterBottomSheetContentBinding.setUpView() {
        chipColorGroup.addColorFilterChips { colorFilter ->
            if (viewModel.selectedColor.value == colorFilter) {
                viewModel.selectColorFilter(null)
            } else {
                viewModel.selectColorFilter(colorFilter)

            }
        }
        chipSortGroup.addSortFilterChips { sortFilter ->
            if (viewModel.selectedSort.value == sortFilter) {
                viewModel.selectSortFilter(null)
            } else {
                viewModel.selectSortFilter(sortFilter)

            }
        }

        viewModel.selectedSort.observe(viewLifecycleOwner) { selectedSort ->
            for (i in 0 until chipSortGroup.childCount) {
                val chip = chipSortGroup.getChildAt(i) as Chip
                chip.isChecked = (i == 0 && selectedSort == SortFilter.RELEVANT) or
                        (i == 1 && selectedSort == SortFilter.LATEST)
            }

        }

        viewModel.selectedColor.observe(viewLifecycleOwner) { selectedColor ->
            for (i in 0 until chipColorGroup.childCount) {
                val chip = chipColorGroup.getChildAt(i) as Chip
                chip.isChecked = selectedColor == getColorFilterFromChipId(
                    chip.id,
                    chipIdToColorFilterMap
                )
            }
        }
    }

    companion object {
        const val TAG = "ModalBottomSheet"
    }
}