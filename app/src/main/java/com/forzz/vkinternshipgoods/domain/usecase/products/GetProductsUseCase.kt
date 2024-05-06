package com.forzz.vkinternshipgoods.domain.usecase.products

import com.forzz.vkinternshipgoods.domain.model.PaginatedProductList
import com.forzz.vkinternshipgoods.domain.repository.ProductRepository
import com.forzz.vkinternshipgoods.domain.usecase.base.SingleUseCase
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository
) : SingleUseCase<PaginatedProductList>() {

    override fun buildUseCaseSingle(): Single<PaginatedProductList> {
        return productRepository.getProducts().subscribeOn(Schedulers.io())
    }
}