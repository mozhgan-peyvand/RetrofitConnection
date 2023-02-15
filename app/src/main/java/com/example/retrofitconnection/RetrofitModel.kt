package com.example.retrofitconnection

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


// data class QuoteList
// according to JSON response

@JsonClass(generateAdapter = true)
data class QuoteList(
    val count: Int,
    val lastItemIndex: Int,
    val page: Int,
    val results: List<Result>,
    val totalCount: Int,
    val totalPages: Int
)

@JsonClass(generateAdapter = true)
data class Result(
    val _id: String,
    val author: String,
    //use for gson library
//    @SerializedName("authorSlug")
    @Json(name = "authorSlug")
    val authorSpecial: String? = null,
    val content: String,
    val dateAdded: String,
    val dateModified: String,
    val length: Int,
    val tags: List<String>
)
