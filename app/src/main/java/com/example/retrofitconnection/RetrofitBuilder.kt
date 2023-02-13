package com.example.retrofitconnection

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory




object RetrofitHelper {
    var gson = GsonBuilder()
        .setLenient()
        .create()
    val baseUrl = "https://quotable.io/"

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            // we need to add converter factory to
            // convert JSON object to Java object
            .build()
    }
}

