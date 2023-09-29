package com.example.demo.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.demo.utility.Utility
import com.google.gson.JsonObject
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class AuthViewModel(appContext: Application) : AndroidViewModel(appContext) {
    private val authRepository = AuthRepository(appContext)

    val isLoading = MutableLiveData(false)
    var errorMessage = MutableLiveData("")
    val jsonObjectResponseLoadEmail = MutableLiveData<JsonObject?>(null)

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        if (!Utility.isConnected()) {
            errorMessage.value = authRepository.noInternet()
        } else {
            errorMessage.value = exception.message
        }
        Utility.printLog("APKBZR", ">> Debug: Exception thrown: $exception.")
    }

    // loadEmail
    fun loadEmail(
        page: String,
        societyId: String,
        type: String,
        mobile_no: String,
        flat_no: String,
        token: String,
        user_token: String
    ) =
        viewModelScope.launch(exceptionHandler) {
            isLoading.value = true
            jsonObjectResponseLoadEmail.value =
                authRepository.loadEmail(
                    page,
                    societyId,
                    type,
                    mobile_no,
                    flat_no,
                    token,
                    user_token
                )
            isLoading.value = false
        }


}