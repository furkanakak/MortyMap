package com.furkan.mortymap.data.remote

import com.furkan.mortymap.data.model.CharacterResponse
import com.furkan.mortymap.data.model.OriginResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("character")
    suspend fun fetchCharacters(@Query("page") page: Int): Response<CharacterResponse>

    @GET("location")
    suspend fun fetchLocation(@Query("page") page: Int): Response<OriginResponse>

}