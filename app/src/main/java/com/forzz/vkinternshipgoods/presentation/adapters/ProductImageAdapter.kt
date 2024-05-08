package com.forzz.vkinternshipgoods.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.forzz.vkinternshipgoods.databinding.ItemProductImageBinding

class ProductImageAdapter(private val images: List<String>) :
    RecyclerView.Adapter<ProductImageAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProductImageBinding.inflate(inflater, parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val context = holder.itemView.context
        holder.onBind(images[position], context)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    inner class ImageViewHolder(private val binding: ItemProductImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(imageUrl: String, context: Context) {
            Glide.with(context).load(imageUrl).into(binding.imageProduct)
        }
    }
}
