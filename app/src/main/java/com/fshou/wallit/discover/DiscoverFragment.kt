package com.fshou.wallit.discover

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.fshou.core.domain.model.Photo
import com.fshou.core.presentation.PhotoAdapter
import com.fshou.core.util.FetchState
import com.fshou.wallit.R
import com.fshou.wallit.databinding.FragmentDiscoverBinding
import org.koin.androidx.viewmodel.ext.android.activityViewModel


class DiscoverFragment : Fragment() {

    private val viewModel by activityViewModel<DiscoverViewModel>()
    private val binding by lazy {
        FragmentDiscoverBinding.inflate(layoutInflater)
    }
    private val rvAdapter by lazy { PhotoAdapter() }
    private val filterBottomSheetFragment = FilterBottomSheetFragment()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.setUpView()
        viewModel.listSearchedPhoto.observe(viewLifecycleOwner, ::handleSearchFetch)

    }

    private fun FragmentDiscoverBinding.setUpView() {
        rvAdapter.onItemClick = { photo ->
            // TODO: PHOTO Argument for
            findNavController().navigate(R.id.detailFragment)
        }
        val staggered = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
        staggered.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
        rvSearchPhoto.setHasFixedSize(true)
        rvSearchPhoto.adapter = rvAdapter
        rvSearchPhoto.layoutManager = staggered
        btnFilter.setOnClickListener {
            activity?.supportFragmentManager?.let {
                filterBottomSheetFragment.show(
                    it, FilterBottomSheetFragment.TAG
                )
            }
        }
        searchBar.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val query = searchBar.text.toString()
                viewModel.setSearchTerm(query)
                true
            } else {
                false
            }
        }
    }

    private fun handleSearchFetch(it: FetchState<List<Photo>>) {
        when (it) {
            is FetchState.Error -> {
                Toast.makeText(requireContext(), it.message.toString(), Toast.LENGTH_SHORT).show()
            }

            is FetchState.Loading -> {
                Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
                binding.rvSearchPhoto.smoothScrollToPosition(0)
            }

            is FetchState.Success -> {
                setListPhoto(it.data)
            }
        }
    }

    private fun setListPhoto(data: List<Photo>?) {
        if (data.isNullOrEmpty()) {
            binding.rvSearchPhoto.visibility = View.GONE
            binding.emptyLayout.root.visibility = View.VISIBLE
            binding.emptyLayout.tvSuggestion.text = getString(R.string.photo_not_found)
            return
        }
        binding.rvSearchPhoto.visibility = View.VISIBLE
        binding.emptyLayout.root.visibility = View.GONE
        rvAdapter.submitList(data)
    }
}
