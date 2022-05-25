package com.example.proyectoandresgonzalez20004118.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.proyectoandresgonzalez20004118.model.ComicRequest
import com.example.proyectoandresgonzalez20004118.model.ComicResponse
import com.example.proyectoandresgonzalez20004118.model.Comics
import com.example.proyectoandresgonzalez20004118.model.RetrofitManager
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListaViewModel(app: Application): BaseViewModel(app) {
    val listadoComics = MutableLiveData<List<Comics>>(arrayListOf())
    val isError = MutableLiveData<Boolean>(false)
    fun getComics(){
        var comics: Call<ComicResponse> = RetrofitManager.create().getComics()
        comics.enqueue(object : Callback<ComicResponse> {
            override fun onResponse(call: Call<ComicResponse>, response: Response<ComicResponse>) {
                listadoComics.value = response?.body()?.data ?: arrayListOf()
            }

            override fun onFailure(call: Call<ComicResponse>, t: Throwable) {
                isError.value = true
            }

        })
    }
    fun deleteComics(nombre : String, editorial: String, fecha: String, descripcion: String){
        Log.d("Nombre", nombre)
        val Tempcomic = ComicRequest(nombre,editorial,fecha,descripcion)
        var comic : Call<ComicResponse> = RetrofitManager.create().deleteComics(Tempcomic)
        comic.enqueue(object : Callback<ComicResponse>{
            override fun onResponse(call: Call<ComicResponse>, response: Response<ComicResponse>) {
                //mostrarMensaje.value = response.body()?.mensaje
                getComics()
            }

            override fun onFailure(call: Call<ComicResponse>, t: Throwable) {
                isError.value = true
                Log.d("Error", t.toString())
            }

        })
    }
}