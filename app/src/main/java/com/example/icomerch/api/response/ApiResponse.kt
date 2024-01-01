package com.example.icomerch.api.response

sealed class ApiResponse<T> {

    // Représente une réponse réussie contenant les données
    data class Success<T>(val data: T) : ApiResponse<T>()

    // Représente une réponse d'erreur, potentiellement avec des informations supplémentaires sur l'erreur
    data class Error<T>(val error: ApiErrorResponse) : ApiResponse<T>()
}

