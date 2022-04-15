package com.android.stockmarketapp.util

/**
 * Helper Generic class used to handle Success and Error case.
 * If we have a Success case then we attach data and if we have error case then we attach
 * message and optional data.
 * If data is loading then we can show loader based on Loading class.
 */
sealed class Resource<T>(val data: T? = null, val message: String? = null){
    class Success<T>(data: T?): Resource<T>(data = data)
    class Error<T>(message: String, data: T?=null): Resource<T>( data = data,message = message)
    class Loading<T>(val isLoading: Boolean = true): Resource<T>(null)

}
