package com.fshou.wallit.detail

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fshou.wallit.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {


    private val viewModel: DetailViewModel by viewModels()
    private val binding by lazy { FragmentDetailBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.setUpView()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    fun FragmentDetailBinding.setUpView(){
        viewModel // TODO
    }
}