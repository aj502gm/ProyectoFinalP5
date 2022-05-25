package com.example.proyectoandresgonzalez20004118.view

import com.example.proyectoandresgonzalez20004118.R
import com.example.proyectoandresgonzalez20004118.model.Comics

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ComicsAdapter(val lista:ArrayList<Comics>, val listener: OnItemContactoClick) : RecyclerView.Adapter<ComicsAdapter.ContactosViewHolder>() {
    class ContactosViewHolder(val view: View, val listener2: OnItemContactoClick): RecyclerView.ViewHolder(view){
        val txtNombre = view.findViewById<TextView>(R.id.nombreComic)
        val txtFecha = view.findViewById<TextView>(R.id.fechaComic)
        val txtDescripcion = view.findViewById<TextView>(R.id.descripcionComic)
        val icon = view.findViewById<ImageView>(R.id.imgLogo)
        fun bind(cont: Comics){
            txtNombre.text = cont.descripcion.toString();
            txtFecha.text = cont.fecha.toString();
            txtDescripcion.text = cont.descripcion.toString();
            view.setOnClickListener{
                listener2.onItemClick(cont)
            }
            icon.setBackgroundResource(if(cont.editorial.toString() == "MARVEL") R.drawable.marvel else if(cont.editorial.toString() == "DC") R.drawable.dc else R.drawable.iamge)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ContactosViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.item_recycler, parent, false),
        listener
    )

    override fun onBindViewHolder(holder: ContactosViewHolder, position: Int) {
        holder.bind(lista[position])
    }

    override fun getItemCount() = lista.size

    fun updateList(newlist: List<Comics>){
        lista.clear();
        lista.addAll(newlist);
        notifyDataSetChanged();
    }
}