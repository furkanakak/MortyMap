package com.furkan.mortymap.domain

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import retrofit2.Response
import kotlin.math.ceil

abstract class BasePagePagingSource<T : Any>() : PagingSource<Int, T>() {

    private companion object { const val START_PAGE = 1 }

    abstract suspend fun fetch(page: Int): List<T>

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> = try {
        val page = params.key ?: START_PAGE
        Log.v("paged21", "page=$page loadSize=${params.loadSize}")
        val data = fetch(page)
        LoadResult.Page(
            data = data,
            prevKey = if (page == START_PAGE) null else page - 1,
            nextKey = if (data.isEmpty()) null else page + 1
        )
    } catch (t: Throwable) {
        LoadResult.Error(t)
    }

    override fun getRefreshKey(state: PagingState<Int, T>): Int? =
        state.anchorPosition?.let { pos ->
            val p = state.closestPageToPosition(pos)
            p?.prevKey?.plus(1) ?: p?.nextKey?.minus(1)
        }
}

object BasePagerFactory {
    fun <T : Any> create(
        pageSize: Int = 20,
        fetch: suspend (page: Int) -> Response<List<T>>
    ) = Pager(
        config = PagingConfig(
            pageSize = pageSize,
            initialLoadSize = pageSize,
            prefetchDistance = 1,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            object : BasePagePagingSource<T>() {
                override suspend fun fetch(page: Int): List<T> {
                    val r = fetch(page)
                    if (r.isSuccessful) return r.body().orEmpty()
                    throw HttpException(r)
                }
            }
        }
    )
}