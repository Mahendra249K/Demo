package com.example.demo.retrofit

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {

    private var logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)


    val okhttpClient =
        OkHttpClient.Builder().addInterceptor(logging).readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)


    val retrofitClient: Retrofit.Builder = Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder()
                    .setLenient()
                    .create()
            )
        )
        .client(okhttpClient.build())


    val apiInterface: ApiInterface = retrofitClient.build().create(ApiInterface::class.java)


}
