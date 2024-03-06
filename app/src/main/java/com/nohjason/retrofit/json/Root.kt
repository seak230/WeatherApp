package com.nohjason.retrofit.json

data class Root(
    val response: Response,
)

data class Response(
    val header: Header,
    val body: Body,
)

data class Header(
    val resultCode: String,
    val resultMsg: String,
)

data class Body(
    val dataType: String,
    val items: Items,
    val pageNo: Long,
    val numOfRows: Long,
    val totalCount: Long,
)

data class Items(
    val item: List<Item>,
)

data class Item(
    val baseDate: String,
    val baseTime: String,
    val category: String,
    val nx: Long,
    val ny: Long,
    val obsrValue: String,
)
