package com.forzz.vkinternshipgoods.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.forzz.vkinternshipgoods.domain.model.Product
import com.forzz.vkinternshipgoods.domain.usecase.products.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductsScreenViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {

    val products = MutableLiveData<List<Product>>()
    val isLoaded = MutableLiveData<Boolean>()

    init {
        isLoaded.postValue(false)
    }

    fun getProducts(skip: Int, limit: Int) {
        isLoaded.postValue(false)
        getProductsUseCase.setParameters(skip, limit)
        getProductsUseCase.execute(
            onSuccess = { productList ->
                isLoaded.postValue(true)
                products.postValue(productList.products)
                Log.d("DATA_RECEIVE_SUCCESS", productList.toString())
            },
            onError = { error ->
                error.printStackTrace()
            }
        )
    }

}
