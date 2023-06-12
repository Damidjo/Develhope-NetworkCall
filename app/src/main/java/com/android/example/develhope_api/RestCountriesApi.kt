package com.android.example.develhope_api

import retrofit2.http.GET

interface RestCountriesApi {
    @GET("all")
    suspend fun getAllCountries(): List<Country>
}