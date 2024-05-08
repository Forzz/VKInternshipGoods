package com.forzz.vkinternshipgoods.data.repository

import com.forzz.vkinternshipgoods.data.remote.ProductService
import com.forzz.vkinternshipgoods.domain.model.PaginatedProductList
import com.forzz.vkinternshipgoods.domain.repository.ProductRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productService: ProductService
) : ProductRepository {

    override fun getProducts(skip: Int, limit: Int): Single<PaginatedProductList> {
        return productService.getProductsRx(skip, limit)
    }
}