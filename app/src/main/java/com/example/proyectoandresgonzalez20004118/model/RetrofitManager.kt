package com.example.proyectoandresgonzalez20004118.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitManager {
    companion object{
        val BASE_URL = "http://172.20.10.10:4000"
        fun create(): ComicsDAO{
            val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(
                BASE_URL).build()
            return retrofit.create(ComicsDAO::class.java)
        }
    }
}