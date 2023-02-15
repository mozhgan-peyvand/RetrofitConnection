package com.example.retrofitconnection

import com.google.gson.annotations.SerializedName


// data class QuoteList
// according to JSON response

data class QuoteList(
    val count: Int,
    val lastItemIndex: Int,
    val page: Int,
    val results: List<Result>,
    val totalCount: Int,
    val totalPages: Int
)

data class Result(
    val _id: String,
    val author: String,
    @SerializedName("authorSlug")
    val authorSpecial: String,
    val content: String,
    val dateAdded: String,
    val dateModified: String,
    val length: Int,
    val tags: List<String>
)




//"id": "1",
//"employee_name": "Jack Full",
//"employee_salary": "300800",
//"employee_age": "61"