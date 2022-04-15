package com.android.stockmarketapp.presentation.company_listing

/**
 * Helper class for all event happening on Company Listing UI screen.
 * i.e clicking on item and then handling on viewModel, swipe down and refresh
 */
sealed class CompanyListingsEvent{
    object Refresh: CompanyListingsEvent()
    data class OnSearchQueryChange( val query: String): CompanyListingsEvent()

}
