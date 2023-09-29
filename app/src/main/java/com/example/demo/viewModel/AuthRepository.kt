package com.example.demo.viewModel

import android.content.Context
import com.example.demo.R
import com.example.demo.retrofit.RetrofitClient
import com.google.gson.JsonObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.await

class AuthRepository(val context: Context) {
    private val call = RetrofitClient.apiInterface


    fun noInternet(): String {
        return context.getString(R.string.no_internet_connection)
    }

    suspend fun loadEmail(
        page: String, societyId: String, type: String, mobile_no: String,
        flat_no: String, token: String, user_token: String
    ): JsonObject =
        withContext(Dispatchers.IO) {
            return@withContext call.loadEmail(
                page, societyId, type, mobile_no, flat_no, token, user_token
            ).await()
        }

}