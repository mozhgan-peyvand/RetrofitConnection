package com.example.retrofitconnection.dataSource

import com.example.retrofitconnection.QuotesApi
import com.example.retrofitconnection.RemoteDataSource
import com.example.retrofitconnection.network.Resource
import com.google.gson.Gson
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RemoteDataSouceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiClient: QuotesApi

    private var client = OkHttpClient.Builder().build()
    var gson: Gson = Gson()

    @Before
    fun createServer() {
        mockWebServer = MockWebServer()

        apiClient = Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(mockWebServer.url("/"))
            .build()
            .create(QuotesApi::class.java)
    }

    @After
    fun shutdownServer() {
        mockWebServer.shutdown()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `correct Quotes list response is parsed into success result`() = runTest {
        val response = MockResponse()
            .setBody(successfulQuoteListResponse)
            .setResponseCode(200)
        mockWebServer.enqueue(response)

        val remoteDataSource = RemoteDataSource(apiClient)
        val expectedResult = QuoteList

        val result = remoteDataSource.getQuotesList()

        assert(result is Resource.Success)
        assertEquals((result as Resource.Success).data, expectedResult)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `malformed response return json error result`() = runTest {

        val response = MockResponse()
            .setBody(errorResponse)
            .setResponseCode(200)
        mockWebServer.enqueue(response)

        val remoteDataSource = RemoteDataSource(apiClient)
        val result = remoteDataSource.getQuotesList()

        assert(result is Resource.Error)
    }

}