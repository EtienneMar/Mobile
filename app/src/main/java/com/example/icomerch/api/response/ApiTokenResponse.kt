package com.example.icomerch.api.response

import com.google.gson.annotations.SerializedName

data class ApiTokenResponse(
    @SerializedName("token") var token : String? = null

)
