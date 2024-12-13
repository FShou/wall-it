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
    private var _binding: FragmentBookmarkBinding? = null
    private val binding get() = _binding!!
    private var rvAdapter: PhotoAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(bookmarkModule)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookmarkBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onDestroyView() {
        binding.rvSearchPhoto.adapter = null
        _binding = null
        rvAdapter = null
        super.onDestroyView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvAdapter = PhotoAdapter()
        binding.setUpView()
        viewModel.listBookmarkedPhoto.observe(viewLifecycleOwner) {
           binding.setListPhoto(it)
        }
    }
    private fun FragmentBookmarkBinding.setUpView(){
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

    }

    private fun FragmentBookmarkBinding.setListPhoto(data: List<Photo>?) {
        if (data.isNullOrEmpty()) {
            rvSearchPhoto.visibility = View.GONE
            emptyLayout.root.visibility = View.VISIBLE
            emptyLayout.tvSuggestion.text = getString(R.string.bookmark_empty)
            return
        }
        rvSearchPhoto.visibility = View.VISIBLE
        emptyLayout.root.visibility = View.GONE
        rvAdapter?.submitList(data)
    }
}