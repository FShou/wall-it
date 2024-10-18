package com.fshou.wallit.discover

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fshou.wallit.R
import com.fshou.wallit.databinding.FragmentDiscoverBinding

class DiscoverFragment : Fragment() {

    private val viewModel: DiscoverViewModel by viewModels()
    private val binding by lazy {
        FragmentDiscoverBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
        binding.setUpView()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    fun FragmentDiscoverBinding.setUpView(){

    }
}