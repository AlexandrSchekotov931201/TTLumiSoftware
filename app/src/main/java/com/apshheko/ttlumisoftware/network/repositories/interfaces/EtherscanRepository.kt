package com.apshheko.ttlumisoftware.network.repositories.interfaces

import com.apshheko.ttlumisoftware.models.ApiResult
import com.apshheko.ttlumisoftware.models.TransactionsResponse

interface EtherscanRepository {
    suspend fun getTransactions(
        address: String,
        startBlock: String,
        endBlock: String,
        page: String,
        offset: String,
        sort: String
    ): ApiResult<TransactionsResponse>
}
