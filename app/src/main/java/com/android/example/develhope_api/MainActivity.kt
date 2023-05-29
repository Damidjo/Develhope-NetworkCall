package com.android.example.develhope_api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import retrofit2.Retrofit
import kotlinx.coroutines.*
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://restcountries.com/v3.1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(RestCountriesApi::class.java)

        coroutineScope.launch {
            try {
                val countries = api.getAllCountries()
                for (country in countries) {
                    println("Nome: ${country.name.official}")
                    println("-------------------------")
                }
            } catch (e: Exception) {
                println("Error: ${e.message}")
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel()
    }
}
