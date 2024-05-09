package com.forzz.vkinternshipgoods.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.forzz.vkinternshipgoods.R
import com.forzz.vkinternshipgoods.databinding.FragmentProductsScreenBinding
import com.forzz.vkinternshipgoods.presentation.adapters.ProductsAdapter
import com.forzz.vkinternshipgoods.presentation.viewmodel.ProductsScreenViewModel
import com.forzz.vkinternshipgoods.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsScreenFragment : Fragment() {

    private lateinit var binding: FragmentProductsScreenBinding
    private var productsAdapter: ProductsAdapter? = null
    private val viewModel: ProductsScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentProductsScreenBinding.inflate(inflater, container, false)

        val layoutManager = GridLayoutManager(requireContext(), 2)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (productsAdapter?.getItemViewType(position) == Constants.ViewType.LOADING) {
                    layoutManager.spanCount
                } else {
                    1
                }
            }
        }

        binding.recyclerViewProducts.layoutManager = layoutManager
        productsAdapter = ProductsAdapter { navigateToProductDetailsScreen() }
        binding.recyclerViewProducts.adapter = productsAdapter
        viewModel.getProducts(0, Constants.PAGE_SIZE)

        binding.recyclerViewProducts.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
                    val currentPage = totalItemCount / Constants.PAGE_SIZE
                    productsAdapter?.setLoading(true)
                    viewModel.getProducts(currentPage * Constants.PAGE_SIZE, Constants.PAGE_SIZE)
                }
            }
        })

        viewModel.isLoaded.observe(viewLifecycleOwner) {
            if (it) productsAdapter?.setLoading(false)
        }

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewModel) {
            this.products.observe(viewLifecycleOwner) {
                productsAdapter?.addData(it)
            }
        }
    }

    private fun navigateToProductDetailsScreen() {

    }
}