package com.furkan.mortymap.domain

sealed class ResponseWrapper<out T : Any> {
    data class Success<out T : Any>(val data: T) : ResponseWrapper<T>()
    data class Error(val error: ResponseError) : ResponseWrapper<Nothing>()
    val isSuccess: Boolean get() = this is Success
}

sealed class ResponseError {
    data object EmptyBody : ResponseError()
    data class Http(val code: Int, val body: String?) : ResponseError()
    data class Network(val cause: Throwable) : ResponseError()
    data class Unknown(val cause: Throwable) : ResponseError()
}

inline fun <A : Any, B : Any> ResponseWrapper<A>.map(
    transform: (A) -> B
): ResponseWrapper<B> = when (this) {
    is ResponseWrapper.Success -> ResponseWrapper.Success(transform(data))
    is ResponseWrapper.Error   -> this
}