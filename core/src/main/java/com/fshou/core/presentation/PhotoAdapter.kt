package com.fshou.core.presentation

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import coil3.request.crossfade
import coil3.request.placeholder
import com.fshou.core.databinding.PhotoItemBinding
import com.fshou.core.domain.model.Photo

class PhotoAdapter: ListAdapter<Photo,PhotoAdapter.PhotoItemViewHolder>(diffUtil) {

    var onItemClick: ((Photo) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoItemViewHolder
        = PhotoItemViewHolder(
            PhotoItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: PhotoItemViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }



    inner class PhotoItemViewHolder(private val binding: PhotoItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: Photo){

            binding.apply {
                val color = Color.parseColor(data.color)
                card.setCardBackgroundColor(color)
                ivThumbnail.load(data.urlRegular) {
                    crossfade(500)
                }
                val width = binding.ivThumbnail.layoutParams.width
                val height = (width * data.height / data.width)
                ivThumbnail.layoutParams.height = height
                ivThumbnail.requestLayout()
            }
            itemView.setOnClickListener { onItemClick?.invoke(data) }
        }
    }

    companion object {
        val diffUtil : DiffUtil.ItemCallback<Photo> = object : DiffUtil.ItemCallback<Photo>() {
            override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
               return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
               return oldItem == newItem
            }

        }
    }

}