package com.furkan.mortymap.data.repository

import android.util.Log
import androidx.paging.PagingData
import com.furkan.mortymap.data.model.Character
import com.furkan.mortymap.data.remote.ApiService
import com.furkan.mortymap.domain.BasePagerFactory
import com.furkan.mortymap.domain.repository.BaseRepository
import com.furkan.mortymap.domain.repository.IRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val service: ApiService
) : BaseRepository(), IRepository {
    override fun fetchPagedCharacters(): Flow<PagingData<Character>> =
        BasePagerFactory.create { page ->
            Log.v("paged12","page $page")
            val list = service.fetchCharacters(page).body()?.results.orEmpty()
            retrofit2.Response.success(list)
        }.flow
}