package com.android.stockmarketapp.di

import com.android.stockmarketapp.data.csv.CSVParser
import com.android.stockmarketapp.data.csv.CompanyListingsParser
import com.android.stockmarketapp.data.csv.IntradayInfoParser
import com.android.stockmarketapp.data.repository.StockRepositoryImpl
import com.android.stockmarketapp.domain.model.CompanyListing
import com.android.stockmarketapp.domain.model.IntradayInfo
import com.android.stockmarketapp.domain.repository.StockRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindCompanyListingParser(
        companyListingsParser: CompanyListingsParser
    ):CSVParser<CompanyListing>

    @Binds
    @Singleton
    abstract fun bindIntradayInfoParser(
        intradayInfoParser: IntradayInfoParser
    ): CSVParser<IntradayInfo>

    @Binds
    @Singleton
    abstract fun bindStockRepository(
        stockRepositoryImpl: StockRepositoryImpl
    ):StockRepository

}