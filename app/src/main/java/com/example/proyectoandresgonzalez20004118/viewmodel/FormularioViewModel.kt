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

class FormularioViewModel(app: Application): BaseViewModel(app) {
    val mostrarMensaje = MutableLiveData<String>("")
    val isError = MutableLiveData<Boolean>(false)

    fun uploadComic(nombre : String, editorial: String, fecha: String, descripcion: String){
        val Tempcomic = ComicRequest(nombre,editorial,fecha,descripcion)
        var comic : Call<ComicResponse> = RetrofitManager.create().saveComic(Tempcomic)
        comic.enqueue(object : Callback<ComicResponse>{
            override fun onResponse(call: Call<ComicResponse>, response: Response<ComicResponse>) {
                //mostrarMensaje.value = response.body()?.mensaje
                Log.d("Exito", "Ok")
            }

            override fun onFailure(call: Call<ComicResponse>, t: Throwable) {
                isError.value = true
                Log.d("Error", t.toString())
            }

        })
    }
}