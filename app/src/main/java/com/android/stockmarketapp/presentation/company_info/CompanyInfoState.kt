package com.android.stockmarketapp.presentation.company_info

import com.android.stockmarketapp.domain.model.CompanyInfo
import com.android.stockmarketapp.domain.model.IntradayInfo

data class CompanyInfoState(
    val stockInfo: List<IntradayInfo> = emptyList(),
    val company: CompanyInfo? =null,
    val isLoading: Boolean = false,
    val error: String? = null
)
