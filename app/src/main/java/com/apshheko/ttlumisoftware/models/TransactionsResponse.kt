package com.apshheko.ttlumisoftware.models

import com.google.gson.annotations.SerializedName

data class TransactionsResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("result")
    val transactions: List<Transaction>
)

data class Transaction(
    @SerializedName("blockNumber")
    val blockNumber: String,
    @SerializedName("timeStamp")
    val timestamp: String,
    @SerializedName("hash")
    val hash: String,
    @SerializedName("nonce")
    val nonce: String,
    @SerializedName("blockHash")
    val blockHash: String,
    @SerializedName("transactionIndex")
    val transactionIndex: String,
    @SerializedName("from")
    val from: String,
    @SerializedName("to")
    val to: String,
    @SerializedName("value")
    val value: String,
    @SerializedName("gas")
    val gas: String,
    @SerializedName("gasPrice")
    val gasPrice: String,
    @SerializedName("isError")
    val isError: String,
    @SerializedName("txreceipt_status")
    val receiptStatus: String,
    @SerializedName("input")
    val input: String,
    @SerializedName("contractAddress")
    val contractAddress: String,
    @SerializedName("cumulativeGasUsed")
    val cumulativeGasUsed: String,
    @SerializedName("gasUsed")
    val gasUsed: String,
    @SerializedName("confirmations")
    val confirmations: String,
    @SerializedName("methodId")
    val methodId: String,
    @SerializedName("functionName")
    val functionName: String
)
