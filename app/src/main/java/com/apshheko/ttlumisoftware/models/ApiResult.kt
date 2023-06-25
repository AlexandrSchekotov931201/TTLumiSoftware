package com.apshheko.ttlumisoftware.models

sealed class ApiResult<T> {
    class Success<T>(val data: T) : ApiResult<T>()
    class Error<T>(val error: Exception) : ApiResult<T>()
}
