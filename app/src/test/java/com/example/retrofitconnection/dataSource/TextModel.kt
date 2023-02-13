package com.example.retrofitconnection.dataSource

import com.example.retrofitconnection.QuoteList
import com.example.retrofitconnection.Result

val QuoteList = QuoteList(
    count = 1,
    lastItemIndex = 0,
    page = 0,
    results = listOf(
        Result(
            _id = "lpxXplIA4ie",
            author = "Yogi Berra",
            authorSlug = "yogi-berra",
            content = "Half the lies they tell about me aren't true.",
            dateAdded = "2022-07-06",
            dateModified = "2022-07-08",
            length = 45,
            tags = listOf("sports", "competition")
        )
    ),
    totalPages = 1,
    totalCount = 1
)