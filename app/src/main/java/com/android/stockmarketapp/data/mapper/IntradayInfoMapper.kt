package com.android.stockmarketapp.data.mapper

import android.os.Build
import androidx.annotation.RequiresApi

import com.android.stockmarketapp.data.remote.dto.IntradayInfoDto
import com.android.stockmarketapp.domain.model.IntradayInfo
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


@RequiresApi(Build.VERSION_CODES.O)
fun IntradayInfoDto.toIntradayInfo():IntradayInfo{
    val pattern = "yyyy-MM-dd HH:mm:ss"
    val formatter = DateTimeFormatter.ofPattern(pattern, Locale.getDefault())
    val localDateTime = LocalDateTime.parse(timestamp,formatter)
    return IntradayInfo(
        date = localDateTime,
        close = close
    )

}