package com.forzz.vkinternshipgoods.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.forzz.vkinternshipgoods.domain.model.PaginatedProductList
import com.forzz.vkinternshipgoods.domain.model.Product
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.Exception
import javax.inject.Inject

class PostPagingDataSource @Inject constructor(
    private val productService: ProductService
) : PagingSource<Int, Product>() {

    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        try {
            val skip = params.key ?: 0
            val limit = params.loadSize

            val response: Single<PaginatedProductList> = productService.getProductsRx(skip, limit)
                .subscribeOn(Schedulers.io())

            val result = response.blockingGet()

            val nextPage = if (result.products.isNotEmpty()) skip + 1 else null

            return LoadResult.Page(
                data = result.products,
                prevKey = if (skip > 0) skip - 1 else null,
                nextKey = nextPage
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}