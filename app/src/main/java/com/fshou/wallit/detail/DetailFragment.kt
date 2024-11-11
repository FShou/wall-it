package com.fshou.wallit.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.GestureDetector
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import coil3.load
import coil3.request.crossfade
import coil3.request.transformations
import coil3.transform.CircleCropTransformation
import com.fshou.core.domain.model.Photo
import com.fshou.core.util.FetchState
import com.fshou.wallit.R
import com.fshou.wallit.databinding.FragmentDetailBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {


    private val viewModel: DetailViewModel by viewModel()
    private val binding by lazy { FragmentDetailBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.setUpView()
        val photoId = arguments?.getString("photoId")
        if (photoId != null) viewModel.getPhotoDetail(photoId)
            .observe(viewLifecycleOwner, ::handleFetchState)

    }

    private fun handleFetchState(fetchState: FetchState<Photo>) {
        when (fetchState) {
            is FetchState.Error -> {
                // TODO: Error
            }

            is FetchState.Loading -> {
                Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
            }

            is FetchState.Success -> fetchState.data?.let { viewModel.setPhoto(it) }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun FragmentDetailBinding.setUpView() {
        val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)

        val gestureDetector =
            GestureDetector(requireContext(), object : GestureDetector.SimpleOnGestureListener() {
                override fun onScroll(
                    e1: MotionEvent?,
                    e2: MotionEvent,
                    distanceX: Float,
                    distanceY: Float
                ): Boolean {
                    super.onScroll(e1, e2, distanceX, distanceY)
                    if (distanceY > 0) {
                        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                    } else if (distanceY < 0) {
                        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                    }
                    return true
                }

            })
        ivPhoto.setOnTouchListener { _, event ->
            gestureDetector.onTouchEvent(event)
            true
        }
        btnBookmark.setOnClickListener {
            viewModel.toggleBookmark()
        }
        btnWallpaper.setOnClickListener {
            // TODO: start wallpaper activity intent
        }

        viewModel.isPhotoBookmarked.observe(viewLifecycleOwner) {
            if (it) {
                btnBookmark.text = getString(R.string.remove_from_bookmark)
            } else {
                btnBookmark.text = getString(R.string.add_to_bookmark)
            }
        }


        viewModel.photo.observe(viewLifecycleOwner) {
            if (it != null) {

                println("PHOTO $it")

                var location: String? = null
                if (!it.city.isNullOrEmpty() && !it.country.isNullOrEmpty()) {
                    location = "${it.city}, ${it.country}"
                }

                ivPhoto.load(it.urlRegular) {
                    crossfade(true)
                }
                ivUser.load(it.userProfileImageUrl) {
                    transformations(CircleCropTransformation())
                    crossfade(true)
                }
                tvUsername.text = it.username
                location?.let {
                    tvLocation.text = location
                }
            }
        }

    }
}