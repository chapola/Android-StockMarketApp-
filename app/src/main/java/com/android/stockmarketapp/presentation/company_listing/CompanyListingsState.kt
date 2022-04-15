package com.android.stockmarketapp.presentation.company_listing

import com.android.stockmarketapp.domain.model.CompanyListing

/**
 * Helper class that belong to every state of Company Listing screen.
 */
data class CompanyListingsState(
    val companies: List<CompanyListing>  = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery: String = ""
)
