package com.fshou.wallit.discover

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.fshou.core.presentation.PhotoAdapter
import com.fshou.core.util.FetchState
import com.fshou.wallit.R
import com.fshou.wallit.databinding.FragmentDiscoverBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DiscoverFragment : Fragment() {

    private val viewModel by viewModel<DiscoverViewModel>()
    private val binding by lazy {
        FragmentDiscoverBinding.inflate(layoutInflater)
    }
    private val rvAdapter by lazy {  PhotoAdapter() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
        binding.setUpView()
        viewModel.listSearchedPhoto.observeForever {
            when(it) {
                is FetchState.Error -> { Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()}
                is FetchState.Loading -> { Toast.makeText(requireContext(),"Loading", Toast.LENGTH_SHORT).show()}
                is FetchState.Success -> {
                    rvAdapter.submitList(it.data)
                }
            }
        }
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    private fun FragmentDiscoverBinding.setUpView(){
        rvAdapter.onItemClick = {
            findNavController().navigate(R.id.detailFragment)
        }
        val staggered = StaggeredGridLayoutManager(2,RecyclerView.VERTICAL)
        rvSearchPhoto.adapter = rvAdapter
        rvSearchPhoto.layoutManager = staggered
    }
}