package com.forzz.vkinternshipgoods.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.forzz.vkinternshipgoods.domain.usecase.products.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductsScreenViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {

    fun getProducts() {
        getProductsUseCase.execute(
            onSuccess = {
                Log.d("DATA_RECEIVE_SUCCESS", it.toString())
            },
            onError = {
                Log.d("DATA_RECEIVE_FAILED", it.message.toString())
            }
        )
    }
}