package com.forzz.vkinternshipgoods.domain.repository

import com.forzz.vkinternshipgoods.domain.model.PaginatedProductList
import io.reactivex.rxjava3.core.Single

interface ProductRepository {
    fun getProducts(skip: Int, limit: Int): Single<PaginatedProductList>
}