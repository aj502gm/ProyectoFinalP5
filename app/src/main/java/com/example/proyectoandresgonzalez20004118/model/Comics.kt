package com.example.proyectoandresgonzalez20004118.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


data class Comics(
    @SerializedName("id")
    var id: Int?,
    @SerializedName("nombre")
    val nombre : String?,
    @SerializedName("editorial")
    val editorial: String?,
    @SerializedName("fecha")
    val fecha: String?,
    @SerializedName("descripcion")
    val descripcion: String?,
)