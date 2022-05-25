package com.example.proyectoandresgonzalez20004118.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import retrofit2.Call
import retrofit2.http.*

@Dao
interface ComicsDAO {
    @GET("comics")
    fun getComics(): Call<ComicResponse>
    @Headers("Content-Type: application/json")
    @POST("comics")
    fun saveComic(@Body comic: ComicRequest): Call<ComicResponse>
    @HTTP(method = "DELETE", path = "comics", hasBody = true)
    fun deleteComics(@Body comicName: ComicRequest): Call<ComicResponse>
}