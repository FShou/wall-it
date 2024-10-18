package com.fshou.core.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil3.load
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
            itemView.setOnClickListener { onItemClick?.invoke(data) }
            binding.apply {
                tvDescription.text = data.description
                ivThumbnail.load(data.urlRegular)
            }
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