package com.example.retrofitconnection

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET


interface RetrofitService {
    @GET("/quotes")
    suspend fun getQuotes() : Response<QuoteList>
}


