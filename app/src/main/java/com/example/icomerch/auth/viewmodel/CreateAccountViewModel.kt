package com.example.icomerch.auth.viewmodel

import User
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.icomerch.api.RetrofitService
import kotlinx.coroutines.Dispatchers
import androidx.lifecycle.viewModelScope
//import com.auth0.android.jwt.JWT
import com.example.icomerch.api.TokenManager
import com.example.icomerch.api.response.ApiErrorResponse
import com.example.icomerch.api.response.ApiResponse
import com.example.icomerch.api.response.ApiTokenResponse
import com.example.icomerch.api.response.NetworkUtils.handleApiError
import com.example.icomerch.model.Role
import kotlinx.coroutines.launch
import retrofit2.Response


class CreateAccountViewModel(application: Application) : AndroidViewModel(application) {

    private val _snackbarMessage = MutableLiveData<String>()
    val snackbarMessage: LiveData<String> = _snackbarMessage

    private val _navigateToSupplier = MutableLiveData<Boolean>()
    val navigateToSupplier: LiveData<Boolean> = _navigateToSupplier

    private val _navigateToClient = MutableLiveData<Boolean>()
    val navigateToClient: LiveData<Boolean> = _navigateToClient


    fun handleInscription(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            val response: Response<ApiTokenResponse>
            try {
                response = RetrofitService.apiInterface.register(user)
            } catch (e: Exception) {
                Log.d("CreateAccountViewModel", e.toString())
                // Gérer les exceptions ici (par exemple, une erreur de réseau)
                return@launch
            }
            if (response.isSuccessful) {
                // La requête a réussi, traiter la réponse
                val successResponse = response.body() // successResponse est de type YourSuccessResponseType
                Log.d("CreateAccountViewModel", successResponse.toString())
            } else {
                // La requête a échoué, traiter l'erreur
                val apiError = handleApiError(response)
                Log.d("CreateAccountViewModel", apiError.toString())
            }
        }
    }

            /*
            try {
                Log.d("CreateAccountViewModel", "Starting API call")
                val response = RetrofitService.apiInterface.register(user)
                Log.d("CreateAccountViewModel", "API Response: $response")
                when (response) {
                    is ApiResponse.Success -> {
                        val token = response.data.token
                        if (token != null) {
                            Log.d("CreateAccountViewModel", "Token: $token")
                            TokenManager.storeToken(getApplication(), token)
                            Log.d("CreateAccountViewModel", "Token stored successfully")
                            _navigateToSupplier.postValue(true)
                            //claimsRole(token)
                        } else {
                            Log.d("CreateAccountViewModel", "Token not found in response")
                        }
                    }

                    is ApiResponse.Error -> {
                        Log.d(
                            "CreateAccountViewModel",
                            "Error HttpStatus: ${response.error.httpStatus}"
                        )
                        Log.d("CreateAccountViewModel", "Error Message: ${response.error.message}")
                        Log.d(
                            "CreateAccountViewModel",
                            "Error Timestamp: ${response.error.timestamp}"
                        )
                        _snackbarMessage.postValue(
                            "Erreur ${response.error.httpStatus}: ${response.error.message}"
                        )
                    }
                }
            } catch (e: Exception) {
                Log.e("CreateAccountViewModel", "Exception during API call", e)
            }
        }
    }


        viewModelScope.launch(Dispatchers.IO) {
            when (val response = RetrofitService.apiInterface.register(user)) {
                is ApiResponse.Success -> {
                    val token = response.data.token
                    if (token != null) {
                        Log.d("CreateAccountViewModel" , token)
                        TokenManager.storeToken(getApplication(), token)
                        Log.d("CreateAccountViewModel", "Token stored successfully")
                        _navigateToSupplier.postValue(true)
                        //claimsRole(token)
                    } else {
                        Log.d("CreateAccountViewModel", "Token not found in response")
                    }
                }

                is ApiResponse.Error -> {
                    Log.d("CreateAccountViewModel", "${response.error.httpStatus}")
                    Log.d("CreateAccountViewModel", "${response.error.message}")
                    Log.d("CreateAccountViewModel", "${response.error.timestamp}")
                    _snackbarMessage.postValue(
                        "Erreur ${response.error.httpStatus}: " +
                                "${response.error.message}"
                    )

                }
            }
        }
    }
*/
        /*
    private fun claimsRole(token : String){
        val jwt = JWT(token)
        val role = jwt.getClaim("role").asString()
        if(role == Role.SUPPLIER.toString()){
            _navigateToSupplier.postValue(true)
        }else if(role == (Role.CLIENT.toString())){
            _navigateToClient.postValue(true)
        }

    }
    */

        fun createUser(
            type: String,
            lastName: String,
            firstName: String,
            email: String,
            password: String,
            postCode: String
        ): User {
            return User(
                role = type,
                lastName = lastName,
                firstName = firstName,
                email = email,
                password = password,
                postcode = postCode,
                interestsCenter = null
            )
        }

    }
