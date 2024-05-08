package com.forzz.vkinternshipgoods.domain.usecase.products

import com.forzz.vkinternshipgoods.domain.model.PaginatedProductList
import com.forzz.vkinternshipgoods.domain.model.Product
import com.forzz.vkinternshipgoods.domain.repository.ProductRepository
import com.forzz.vkinternshipgoods.domain.usecase.base.SingleUseCase
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.subscribeOn
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository
) : SingleUseCase<PaginatedProductList>() {

    private var skip: Int = 0
    private var limit: Int = 10

    fun setParameters(skip: Int, limit: Int) {
        this.skip = skip
        this.limit = limit
    }

    override fun buildUseCaseSingle(): Single<PaginatedProductList> {
        return productRepository.getProducts(skip, limit)
    }
}