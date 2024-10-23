package com.fshou.wallit.discover

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fshou.wallit.databinding.FilterBottomSheetContentBinding

class FilterBottomSheetFragment : Fragment() {

    private val binding by lazy {
        FilterBottomSheetContentBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }




    companion object {
        const val TAG = "ModalBottomSheet"
    }
}