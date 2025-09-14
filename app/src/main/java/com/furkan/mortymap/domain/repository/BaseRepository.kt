package com.furkan.mortymap.domain.repository

import com.furkan.mortymap.domain.ResponseError
import com.furkan.mortymap.domain.ResponseWrapper
import retrofit2.Response
import java.io.IOException

abstract class BaseRepository {
    protected suspend inline fun <reified T : Any> net(
        crossinline block: suspend () -> Response<T>
    ): ResponseWrapper<T> = try {
        val resp = block()
        if (resp.isSuccessful) {
            resp.body()?.let { ResponseWrapper.Success(it) }
                ?: ResponseWrapper.Error(ResponseError.EmptyBody)
        } else {
            ResponseWrapper.Error(ResponseError.Http(resp.code(), resp.errorBody()?.string()))
        }
    } catch (io: IOException) {
        ResponseWrapper.Error(ResponseError.Network(io))
    } catch (t: Throwable) {
        ResponseWrapper.Error(ResponseError.Unknown(t))
    }
}