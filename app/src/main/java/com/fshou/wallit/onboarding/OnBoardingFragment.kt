package com.fshou.wallit.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.fshou.wallit.MainActivity
import com.fshou.wallit.databinding.FragmentOnBoardingBinding
import com.fshou.wallit.utils.applyMarginAndScalePageTransformer
import com.fshou.wallit.utils.onPageSelected
import com.fshou.wallit.utils.setCurrentItemWithSmoothScroll
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class OnBoardingFragment : Fragment() {


    private val viewModel: OnBoardingViewModel by viewModel()
    private val binding: FragmentOnBoardingBinding by lazy {
        FragmentOnBoardingBinding.inflate(layoutInflater)
    }
    private var pageChangeJob: Job? = null

    companion object {
        private const val SLIDE_DELAY = 2000L
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        pageChangeJob?.cancel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.setupView()
    }

    private fun restartPageChangeCoroutine() {
        pageChangeJob?.cancel()
        pageChangeJob = lifecycleScope.launch {
            delay(SLIDE_DELAY)
            slideToNextPage()
        }
    }

    private fun slideToNextPage() {
        with(binding.vpCarousel) {
            val nextItem = (currentItem + 1) % adapter!!.itemCount
            setCurrentItemWithSmoothScroll(nextItem)
        }
    }


    private fun FragmentOnBoardingBinding.setupView(){
        with(vpCarousel) {
            adapter = CarouselAdapter()
            onPageSelected { restartPageChangeCoroutine() }
            applyMarginAndScalePageTransformer()
        }

        btnContinue.setOnClickListener {
            startActivity(Intent(requireActivity(), MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or  Intent.FLAG_ACTIVITY_CLEAR_TASK
            })
        }
    }
}