package com.example.icomerch.api

import User
import com.example.icomerch.api.response.ApiResponse
import com.example.icomerch.api.response.ApiTokenResponse
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.Call
import retrofit2.Response

interface IIcomerchApi {
    @POST("/api/auth/register")
    suspend fun register(@Body user: User): Response<ApiTokenResponse>
}


