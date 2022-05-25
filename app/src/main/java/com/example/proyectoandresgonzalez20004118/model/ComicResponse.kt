package com.example.proyectoandresgonzalez20004118.model

data class ComicResponse(
    //EN BASE A LO QUE DEVUELVA EL BACKEND
    val status: Int?,
    val mensaje: String?,
    val data: List<Comics>?,
)
