package com.apshheko.ttlumisoftware.network.repositories.impl

import com.apshheko.ttlumisoftware.network.api.EtherscanApi
import com.apshheko.ttlumisoftware.network.repositories.BaseRepository
import com.apshheko.ttlumisoftware.network.repositories.interfaces.EtherscanRepository
import javax.inject.Inject

class EtherscanRepositoryImpl @Inject constructor(
    private val api: EtherscanApi
) : BaseRepository(), EtherscanRepository {

    override suspend fun getTransactions(
        address: String,
        startBlock: String,
        endBlock: String,
        page: String,
        offset: String,
        sort: String
    ) = safeApiCall {
        api.getListTransactions(
            address = address,
            startBlock = startBlock,
            endBlock = endBlock,
            page = page,
            offset = offset,
            sort = sort,
            apiKey = apiKey
        )
    }
}
