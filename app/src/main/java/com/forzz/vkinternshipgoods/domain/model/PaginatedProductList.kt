package com.forzz.vkinternshipgoods.domain.model

data class PaginatedProductList(
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int
)