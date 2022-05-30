package com.example.ourtable.api

import com.example.ourtable.model.Cook
import retrofit2.Call
import retrofit2.http.GET

interface CookService {


    @GET("/api/d6b4f5552c2b4b549fac/COOKRCP01/json/1/5")
    fun getMoviesByName(): Call<Cook>

}