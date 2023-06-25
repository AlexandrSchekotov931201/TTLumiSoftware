package com.apshheko.ttlumisoftware.network.api

import com.apshheko.ttlumisoftware.models.TransactionsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface EtherscanApi {
    @GET("/api")
    suspend fun getListTransactions(
        @Query("module") module: String = "account",
        @Query("action") action: String = "txlist",
        @Query("address") address: String,
        @Query("startblock") startBlock: String,
        @Query("endblock") endBlock: String,
        @Query("page") page: String,
        @Query("offset") offset: String,
        @Query("sort") sort: String,
        @Query("apikey") apiKey: String
    ): TransactionsResponse
}
