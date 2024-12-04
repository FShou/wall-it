package com.fshou.wallit.bookmark

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.fshou.core.domain.model.Photo
import com.fshou.core.presentation.PhotoAdapter
import com.fshou.wallit.R
import com.fshou.wallit.databinding.FragmentBookmarkBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class BookmarkFragment : Fragment() {

    private val viewModel: BookmarkViewModel by viewModel()
    private val binding by lazy { FragmentBookmarkBinding.inflate(layoutInflater) }
    private val rvAdapter by lazy { PhotoAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(bookmarkModule)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.setUpView()
        viewModel.listBookmarkedPhoto.observe(viewLifecycleOwner) {
           setListPhoto(it)
        }
    }
    private fun FragmentBookmarkBinding.setUpView(){
        rvAdapter.onItemClick = { photo ->
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

    }

    private fun setListPhoto(data: List<Photo>?) {
        if (data.isNullOrEmpty()) {
            binding.rvSearchPhoto.visibility = View.GONE
            binding.emptyLayout.root.visibility = View.VISIBLE
            binding.emptyLayout.tvSuggestion.text = getString(R.string.bookmark_empty)
            return
        }
        binding.rvSearchPhoto.visibility = View.VISIBLE
        binding.emptyLayout.root.visibility = View.GONE
        rvAdapter.submitList(data)
    }
}