package com.fshou.wallit.onboarding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import coil3.request.crossfade
import com.fshou.wallit.R
import com.fshou.wallit.databinding.CarouselItemBinding

class CarouselAdapter: RecyclerView.Adapter<CarouselAdapter.ViewHolder>() {
    private val carouselImgs = listOf(
        R.drawable.img_carousel_1,
        R.drawable.img_carousel_2,
        R.drawable.img_carousel_3,
        R.drawable.img_carousel_4,
        R.drawable.img_carousel_5,
    )

    class ViewHolder(private val binding: CarouselItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(img: Int){
                binding.ivThumbnail.load(img){
                    crossfade(true)
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CarouselItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(carouselImgs[position])
    }

    override fun getItemCount(): Int = carouselImgs.size

}