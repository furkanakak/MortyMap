package com.furkan.mortymap.domain

sealed class ResponseWrapper<out T : Any> {
    data class Success<out T : Any>(val data: T) : ResponseWrapper<T>()
    data class Error(val error: ResponseError) : ResponseWrapper<Nothing>()
}

sealed class ResponseError {
    data object EmptyBody : ResponseError()
    data class Http(val code: Int, val body: String?) : ResponseError()
    data class Network(val cause: Throwable) : ResponseError()
    data class Unknown(val cause: Throwable) : ResponseError()
}