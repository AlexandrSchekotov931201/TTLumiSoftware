package com.apshheko.ttlumisoftware.extencions

import java.math.BigDecimal

fun String.toEHT(): String {
    val value = BigDecimal(this)
    val weiToEth = BigDecimal("1e18")
    return value.divide(weiToEth).stripTrailingZeros().toPlainString()
}
