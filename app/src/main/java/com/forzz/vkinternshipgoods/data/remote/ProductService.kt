package com.forzz.vkinternshipgoods.data.remote

import com.forzz.vkinternshipgoods.domain.model.PaginatedProductList
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface ProductService {
    @GET("products")
    fun getProducts(): Single<PaginatedProductList>
}