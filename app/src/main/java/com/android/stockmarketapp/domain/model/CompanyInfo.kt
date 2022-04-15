package com.android.stockmarketapp.domain.model

import com.squareup.moshi.Json
import java.text.DateFormatSymbols

data class CompanyInfo(
   val symbol: String,
   val description: String,
   val name: String,
   val country: String,
   val industry: String
)
