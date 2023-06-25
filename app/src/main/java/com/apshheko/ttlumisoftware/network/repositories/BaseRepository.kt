package com.apshheko.ttlumisoftware.network.repositories

import com.apshheko.ttlumisoftware.models.ApiResult

abstract class BaseRepository {

    protected val apiKey: String = "" // ВАШ api key token

    suspend fun <T> safeApiCall(apiToBeCalled: suspend () -> T): ApiResult<T> = try {
        val result = apiToBeCalled.invoke()
        ApiResult.Success(data = result)
    } catch (e: Exception) {
        ApiResult.Error(e)
    }
}
