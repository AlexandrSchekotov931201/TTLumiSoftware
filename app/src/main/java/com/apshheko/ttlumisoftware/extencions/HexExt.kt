package com.apshheko.ttlumisoftware.extencions

fun String.isEmptyInputData() = this.replace("0x", "").isNotEmpty()

fun String.removeExtraZerosInInputData() = this.replaceFirst("^0x0+".toRegex(), "0x")

fun String.getMethodIdFormInputData() = this.substring(0, 10)
