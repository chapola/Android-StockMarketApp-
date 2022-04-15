package com.android.stockmarketapp.data.repository

import com.android.stockmarketapp.data.csv.CSVParser
import com.android.stockmarketapp.data.csv.CompanyListingsParser
import com.android.stockmarketapp.data.csv.IntradayInfoParser
import com.android.stockmarketapp.data.local.StockDatabase
import com.android.stockmarketapp.data.mapper.toCompanyInfo
import com.android.stockmarketapp.data.mapper.toCompanyListing
import com.android.stockmarketapp.data.mapper.toCompanyListingEntity
import com.android.stockmarketapp.data.remote.StockApi
import com.android.stockmarketapp.domain.model.CompanyInfo
import com.android.stockmarketapp.domain.model.CompanyListing
import com.android.stockmarketapp.domain.model.IntradayInfo
import com.android.stockmarketapp.domain.repository.StockRepository
import com.android.stockmarketapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepositoryImpl @Inject constructor(
    private val api: StockApi,
    private val db: StockDatabase,
    private val companyListingsParser: CSVParser<CompanyListing>,
    private val intradayInfoParser: CSVParser<IntradayInfo>
): StockRepository {
    private val dao = db.dao

    override suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListing>>> {
        return flow {
            // emit initial loading
            emit(Resource.Loading(true))
            // get local list from database
            val localListing = dao.searchCompanyListing(query = query)

            // map entity to class and emit success
            emit(Resource.Success(
                data = localListing.map { it.toCompanyListing() }
            ))

            // check is db is empty
            val isDbEmpty = localListing.isEmpty() && query.isBlank()
            
            // only load from cache
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote
            if (shouldJustLoadFromCache){
                emit(Resource.Loading(false))
                return@flow
            }

            // load from remote
            val  remoteListing = try {
                val response = api.getListing()
                companyListingsParser.parse(response.byteStream())
            }catch (e: IOException){
                e.printStackTrace()
                emit(Resource.Error(e.message.toString()))
                null
            }catch (e:HttpException){
                e.printStackTrace()
                emit(Resource.Error(e.message.toString()))
                null
            }

            remoteListing?.let { listing ->
            dao.clearCompanyListing()
            dao.insertCompanyListing(
                listing.map { it.toCompanyListingEntity() }
            )
                emit(Resource.Success(
                    data = dao.searchCompanyListing("")
                        .map { it.toCompanyListing() }
                ))
                emit(Resource.Loading(false))

            }

        }
    }

    override suspend fun getIntradayInfo(symbol: String): Resource<List<IntradayInfo>> {
        return try {
            val response = api.getIntradayInfo(symbol)
            val result = intradayInfoParser.parse(response.byteStream())
            Resource.Success(result)
        }catch (e: IOException){
            e.printStackTrace()
            Resource.Error(
                message = "Couldn't load intraday info"
            )
        }catch (e: HttpException){
            e.printStackTrace()
            Resource.Error(
                message = "Couldn't load intraday info"
            )
        }
    }

    override suspend fun getCompanyInfo(symbol: String): Resource<CompanyInfo> {
        return try {
            val result = api.getCompanyInfo(symbol)
            Resource.Success(result.toCompanyInfo())

        }catch (e: IOException){
            e.printStackTrace()
            Resource.Error(
                message = "Couldn't load company info"
            )
        }catch (e: HttpException){
            e.printStackTrace()
            Resource.Error(
                message = "Couldn't load company info"
            )
        }
    }
}