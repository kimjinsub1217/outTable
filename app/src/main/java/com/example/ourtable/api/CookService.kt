package com.example.ourtable.api

import android.os.Build
import com.example.ourtable.BuildConfig
import com.example.ourtable.model.Cook
import retrofit2.Call
import retrofit2.http.GET

interface CookService {


    @GET("/api/${BuildConfig.ourTable}/COOKRCP01/json/1/20")
    fun getCookByName(): Call<Cook>

    @GET("/api/${BuildConfig.ourTable}/COOKRCP01/json/1/3")
    fun getCookBanner(): Call<Cook>

}