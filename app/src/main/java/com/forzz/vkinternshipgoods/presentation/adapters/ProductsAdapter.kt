package com.forzz.vkinternshipgoods.presentation.adapters

import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.forzz.vkinternshipgoods.databinding.ProductItemBinding
import com.forzz.vkinternshipgoods.domain.model.Product

internal class ProductsAdapter(val onProductClick: (Product) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val products: MutableList<Product> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val productItemBinding = ProductItemBinding.inflate(inflater, parent, false)

        return ProductViewHolder(productItemBinding)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val productViewHolder = holder as ProductViewHolder
        val context = productViewHolder.itemView.context
        productViewHolder.onBind(products[position], context)
    }

    fun addData(list: List<Product>) {
        this.products.clear()
        this.products.addAll(list)
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
}