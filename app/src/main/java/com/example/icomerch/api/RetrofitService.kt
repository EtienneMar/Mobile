package com.example.icomerch.api

//import com.example.icomerch.api.response.ApiResponseCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {

    private val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()
    private const val BASE_URL = "http://10.0.2.2:9000/"


    val apiInterface: IIcomerchApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            //.addCallAdapterFactory(ApiResponseCallAdapterFactory())
            .build()
            .create(IIcomerchApi::class.java)
    }
}
