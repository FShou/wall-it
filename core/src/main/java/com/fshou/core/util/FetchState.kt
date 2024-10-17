package com.fshou.core.util

sealed class FetchState<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : FetchState<T>(data)
    class Loading<T>(data: T? = null) : FetchState<T>(data)
    class Error<T>(message: String, data: T? = null) : FetchState<T>(data, message)
}