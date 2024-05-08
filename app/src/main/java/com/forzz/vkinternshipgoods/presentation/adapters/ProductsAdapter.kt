package com.forzz.vkinternshipgoods.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.forzz.vkinternshipgoods.R
import com.forzz.vkinternshipgoods.databinding.ProductItemBinding
import com.forzz.vkinternshipgoods.domain.model.Product

internal class ProductsAdapter(val onProductClick: (Product) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val products: MutableList<Product> = ArrayList()
    private var isLoading = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_LOADING) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_loading, parent, false)
            LoadingViewHolder(view)
        } else {
            val inflater = LayoutInflater.from(parent.context)
            val productItemBinding = ProductItemBinding.inflate(inflater, parent, false)
            ProductViewHolder(productItemBinding)
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
            VIEW_TYPE_LOADING
        } else {
            VIEW_TYPE_ITEM
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
        private val binding: ProductItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(product: Product, context: Context) {
            binding.root.clipToOutline = true
            binding.textProductName.text = product.title
            Glide.with(context).load(product.images[0]).into(binding.imageProduct)

            itemView.setOnClickListener {
                onProductClick.invoke(product)
            }
        }
    }

    class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    companion object {
        const val VIEW_TYPE_ITEM = 0
        const val VIEW_TYPE_LOADING = 1
    }
}
