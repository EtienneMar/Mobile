package com.example.icomerch.api

import com.example.icomerch.api.response.ApiErrorResponse


sealed class Result<T>(
    val data : T? = null
) {
    class Success<T> (data: T?): Result<T>(data)
    data class Error<T>(val error: ApiErrorResponse) : Result<T>()
}
