package com.forzz.vkinternshipgoods.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.forzz.vkinternshipgoods.R
import com.forzz.vkinternshipgoods.presentation.viewmodel.ProductsScreenViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsScreenFragment : Fragment() {

    private val viewModel: ProductsScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel.getProducts()

        return inflater.inflate(R.layout.fragment_products_screen, container, false)
    }
}