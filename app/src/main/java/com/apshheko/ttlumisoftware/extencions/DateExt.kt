package com.apshheko.ttlumisoftware.extencions

import com.apshheko.ttlumisoftware.models.EnumDateFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun String.timestampToDate(): Date = Date(this.toLong() * 1000)

fun Date.toFormat(format: EnumDateFormat): String {
    return SimpleDateFormat(format.pattern, Locale.getDefault()).format(this)
}
