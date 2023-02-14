package com.example.retrofitconnection

import io.github.metmuseum.themet.common.network.BaseRemoteDataSource
import com.example.retrofitconnection.network.Resource
import com.example.retrofitconnection.network.safeApiCall

class RemoteDataSource constructor(
    private val retrofitService: RetrofitService
) {

    private val baseRemoteDataSource = BaseRemoteDataSource()

    suspend fun getQuotesList() : Resource<QuoteList> = safeApiCall(
        call = { requestQuotesList() },
        errorMessage = "error message for quotes"
    )

    suspend fun requestQuotesList(): Resource<QuoteList> {



        return baseRemoteDataSource.checkApiResult(
            response = retrofitService.getQuotes()
        )
    }
}