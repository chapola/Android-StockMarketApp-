package com.android.stockmarketapp.data.remote

import com.android.stockmarketapp.data.remote.dto.CompanyInfoDto
import com.android.stockmarketapp.domain.model.CompanyInfo
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface StockApi {

    @GET("query?function=LISTING_STATUS")
    suspend fun getListing(
        @Query("apikey") apiKey: String  = API_KEY
    ): ResponseBody

    @GET("query?function=TIME_SERIES_INTRADAY&interval=60min&datatype=csv")
    suspend fun getIntradayInfo(
        @Query("symbol") symbol: String,
        @Query("apikey") apiKey: String = API_KEY,

    ):ResponseBody

    @GET("query?function=OVERVIEW")
    suspend fun getCompanyInfo(
        @Query("symbol") symbol: String,
        @Query("apikey") apiKey: String = API_KEY
    ): CompanyInfoDto

    companion object{
        const val API_KEY = "WW039804XK9G2QZX"
        const val BASE_URL = "https://alphavantage.co"
    }


}