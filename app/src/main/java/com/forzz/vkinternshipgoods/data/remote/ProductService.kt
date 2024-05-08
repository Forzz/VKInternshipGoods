package com.forzz.vkinternshipgoods.data.remote

import com.forzz.vkinternshipgoods.domain.model.PaginatedProductList
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductService {
    @GET("products")
    fun getProductsRx(
        @Query("skip") skip: Int,
        @Query("limit") limit: Int
    ): Single<PaginatedProductList>
}