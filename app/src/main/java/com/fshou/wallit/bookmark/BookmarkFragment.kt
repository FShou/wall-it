package com.fshou.wallit.bookmark

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fshou.wallit.R
import com.fshou.wallit.databinding.FragmentBookmarkBinding

class BookmarkFragment : Fragment() {

    private val viewModel: BookmarkViewModel by viewModels()
    private val binding by lazy { FragmentBookmarkBinding.inflate(layoutInflater) }

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

    fun FragmentBookmarkBinding.setUpView(){
        // TODO: Use the ViewModel

    }
}