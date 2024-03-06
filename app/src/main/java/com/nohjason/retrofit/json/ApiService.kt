package com.nohjason.retrofit.json

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.nohjason.retrofit.API_KEY
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime

private const val BASE_URL =
    "https://android-kotlin-fun-mars-server.appspot.com"
private const val WEATHER_URL = "https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/"

var gson = GsonBuilder().setLenient().create()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create(gson))
//    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(WEATHER_URL)
    .build()

//val dataFormat1 = SimpleDateFormat("yyyyMMdd")
//val now = LocalDateTime.now()
//val nowText = {
//    dataFormat1.
//}()

interface MarsApiService {

    @GET("getUltraSrtNcst")
    suspend fun getData(
        @Query("nx") nx: Int = 55,
        @Query("ny") ny: Int = 127,
        @Query("base_time") time: String = "0600",
        @Query("base_date") date: String, // <- 시간에 따라 자동으로 변환해야함
        @Query("dataType") dataType: String = "JSON",
        @Query("numOfRows") number: Int = 1000,
        @Query("pageNo") page: Int = 1,
        @Query("serviceKey") serviceKey: String = API_KEY
    ): Root
}

object Api {
    val retrofitService: MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java)
    }
}