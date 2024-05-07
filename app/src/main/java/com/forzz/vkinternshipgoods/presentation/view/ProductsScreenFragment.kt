package com.forzz.vkinternshipgoods.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.forzz.vkinternshipgoods.R
import com.forzz.vkinternshipgoods.databinding.FragmentProductsScreenBinding
import com.forzz.vkinternshipgoods.presentation.adapters.ProductsAdapter
import com.forzz.vkinternshipgoods.presentation.viewmodel.ProductsScreenViewModel
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
        binding.recyclerViewProducts.layoutManager = GridLayoutManager(requireContext(), 2)
        productsAdapter = ProductsAdapter { navigateToProductDetailsScreen() }
        binding.recyclerViewProducts.adapter = productsAdapter
        viewModel.getProducts()
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