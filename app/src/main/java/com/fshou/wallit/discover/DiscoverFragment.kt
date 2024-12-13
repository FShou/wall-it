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
    private var _binding: FragmentDiscoverBinding? = null
    private val binding get() = _binding!!
    private var rvAdapter: PhotoAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDiscoverBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvAdapter = PhotoAdapter()
        binding.setUpView()
        viewModel.listSearchedPhoto.observe(viewLifecycleOwner, ::handleSearchFetch)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        rvAdapter = null
    }

    private fun FragmentDiscoverBinding.setUpView() {
        rvAdapter?.onItemClick = { photo ->
            val bundle = Bundle().apply {
                putString("photoId", photo.id)
            }
            findNavController().navigate(R.id.detailFragment, bundle)
        }
        val staggered = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
        staggered.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
        rvSearchPhoto.setHasFixedSize(true)
        rvSearchPhoto.adapter = rvAdapter
        rvSearchPhoto.layoutManager = staggered
        btnFilter.setOnClickListener {
            val filterBottomSheetFragment = FilterBottomSheetFragment()
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
                binding.setListPhoto(it.data)
            }
        }
    }

    private fun FragmentDiscoverBinding.setListPhoto(data: List<Photo>?) {
        if (data.isNullOrEmpty()) {
            rvSearchPhoto.visibility = View.GONE
            emptyLayout.root.visibility = View.VISIBLE
            emptyLayout.tvSuggestion.text = getString(R.string.photo_not_found)
            return
        }
        rvSearchPhoto.visibility = View.VISIBLE
        emptyLayout.root.visibility = View.GONE
        rvAdapter?.submitList(data)
    }
}
