package com.fshou.wallit.discover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fshou.core.util.ColorFilter.Companion.addColorFilterChips
import com.fshou.core.util.ColorFilter.Companion.chipIdToColorFilterMap
import com.fshou.core.util.ColorFilter.Companion.getColorFilterFromChipId
import com.fshou.wallit.databinding.FilterBottomSheetContentBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import org.koin.androidx.viewmodel.ext.android.viewModel

class FilterBottomSheetFragment : BottomSheetDialogFragment() {

    private var _binding: FilterBottomSheetContentBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<DiscoverViewModel>()

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
        chipGroup.addColorFilterChips { colorFilter ->
            if (viewModel.selectedColor.value == colorFilter) {
                viewModel.selectColorFilter(null)
            } else {
                viewModel.selectColorFilter(colorFilter)

            }
        }
        viewModel.selectedColor.observe(viewLifecycleOwner) { selectedColor ->
            for (i in 0 until chipGroup.childCount) {
                val chip = chipGroup.getChildAt(i) as Chip
                chip.isChecked = selectedColor == getColorFilterFromChipId(
                    chip.id,
                    chipIdToColorFilterMap
                ) // Update checked state
            }
        }
    }

    companion object {
        const val TAG = "ModalBottomSheet"
    }
}