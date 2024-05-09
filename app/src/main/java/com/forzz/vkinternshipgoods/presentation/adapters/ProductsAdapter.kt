package com.forzz.vkinternshipgoods.presentation.adapters

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.forzz.vkinternshipgoods.R
import com.forzz.vkinternshipgoods.databinding.ItemProductBinding
import com.forzz.vkinternshipgoods.domain.model.Product
import com.forzz.vkinternshipgoods.utils.Constants
import com.google.android.material.tabs.TabLayoutMediator
import kotlin.math.roundToInt

internal class ProductsAdapter(val onProductClick: (Product) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val products: MutableList<Product> = ArrayList()
    private var isLoading = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == Constants.ViewType.LOADING) {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_loading, parent, false)
            LoadingViewHolder(view)
        } else {
            val inflater = LayoutInflater.from(parent.context)
            val itemProductBinding = ItemProductBinding.inflate(inflater, parent, false)
            ProductViewHolder(itemProductBinding)
        }
    }

    override fun getItemCount(): Int {
        return products.size + if (isLoading) 1 else 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ProductViewHolder) {
            val context = holder.itemView.context
            holder.onBind(products[position], context)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == itemCount - 1 && isLoading) {
            Constants.ViewType.LOADING
        } else {
            Constants.ViewType.ITEM
        }
    }

    fun addData(list: List<Product>) {
        this.products.addAll(list)
        notifyDataSetChanged()
    }

    fun setLoading(isLoading: Boolean) {
        this.isLoading = isLoading
        notifyDataSetChanged()
    }

    inner class ProductViewHolder(
        private val binding: ItemProductBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(product: Product, context: Context) {

            val allProductImages = mutableListOf(product.thumbnail)
            allProductImages.addAll(product.images)

            binding.root.clipToOutline = true
            binding.textPreviousPrice.text = "${product.price}"
            binding.textPreviousPrice.paintFlags =
                binding.textPreviousPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            binding.textCurrentPrice.text =
                "${(product.price - (product.price * (product.discountPercentage / 100))).roundToInt()}$"
            binding.textSalePercentage.text = "-${product.discountPercentage.roundToInt()}%"
            binding.textProductName.text = product.title
            binding.textDescription.text = product.description

            val viewPager = binding.imagesProduct
            val imageAdapter = ProductImageAdapter(allProductImages)
            viewPager.adapter = imageAdapter

            TabLayoutMediator(binding.tabLayoutImageStatus, viewPager) { tab, position -> }.attach()

            itemView.setOnClickListener {
                onProductClick.invoke(product)
            }
        }
    }

    class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}