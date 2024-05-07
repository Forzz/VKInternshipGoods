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

    fun getProducts() {
        getProductsUseCase.execute(
            onSuccess = {
                isLoaded.postValue(true)
                products.postValue(it.products)
                Log.d("DATA_RECEIVE_SUCCESS", it.toString())
            },
            onError = {
                it.printStackTrace()
            }
        )
    }
}