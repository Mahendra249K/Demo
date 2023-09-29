package com.example.demo.retrofit

import androidx.annotation.Keep
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

@Keep
interface ApiInterface {


    //loadEmail
    @GET(Constant.LOAD_EMAILS)
    fun loadEmail(
        @Query("page") page: String,
        @Query("societyId") occid: String,
        @Query("type") type: String,
        @Query("mobile_no", encoded = true) mobile_no: String,
        @Query("flat_no") flat_no: String,
        @Query("token") token: String,
        @Query("user_token") user_token: String
    ): Call<JsonObject>


//    //deleteOccupantDetails
//    class deleteOccupantDetails {
//        var society_id: String = ""
//        var occupant_id: String = ""
//        var token: String = ""
//        var user_token: String = ""
//    }

//    @POST(Constant.DELETE_OCCUPANT_DETAILS)
//    fun deleteOccupantDetails(@Body() request: deleteOccupantDetails): Call<JsonObject>


}

