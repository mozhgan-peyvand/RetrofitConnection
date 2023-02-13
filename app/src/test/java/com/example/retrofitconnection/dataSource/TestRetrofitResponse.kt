package com.example.retrofitconnection.dataSource

val successfulQuoteListResponse = """
{
   "count": 1,
   "lastItemIndex": 0,
    "page": 0,
    "results": [
     {
     "_id": "lpxXplIA4ie",
      "author": "Yogi Berra", 
      "authorSlug": "yogi-berra", 
      "content": "Half the lies they tell about me aren't true.",
       "dateAdded": "2022-07-06", 
       "dateModified": "2022-07-08", 
       "length": 45, 
       "tags": ["sports", "competition"]
       }
       ],
       "totalCount": 1,
       "totalPages": 1
}
""".trimIndent()

val errorResponse = "I am not a json :o"