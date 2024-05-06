package com.forzz.vkinternshipgoods.data.repository

import com.forzz.vkinternshipgoods.data.remote.ProductService
import com.forzz.vkinternshipgoods.domain.model.PaginatedProductList
import com.forzz.vkinternshipgoods.domain.repository.ProductRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productService: ProductService
) : ProductRepository {

    override fun getProducts(): Single<PaginatedProductList> {
        return productService.getProducts()
    }
}