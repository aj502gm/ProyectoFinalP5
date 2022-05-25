package com.example.proyectoandresgonzalez20004118.view

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyectoandresgonzalez20004118.R
import com.example.proyectoandresgonzalez20004118.databinding.FragmentListBinding
import com.example.proyectoandresgonzalez20004118.model.Comics
import com.example.proyectoandresgonzalez20004118.viewmodel.ListaViewModel

class ListFragment : Fragment(), OnItemContactoClick {
    val adapter = ComicsAdapter(arrayListOf(), this)
    lateinit var binding: FragmentListBinding
    lateinit var model: ListaViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        model = ViewModelProvider(this).get(ListaViewModel::class.java)
        binding = FragmentListBinding.inflate(layoutInflater, container, false)
        binding.listado.adapter = adapter
        binding.listado.layoutManager = LinearLayoutManager(context);
        if(isConnectionAvailable(context)){
            model.getComics()
            model.listadoComics.observe(viewLifecycleOwner, {
                adapter.updateList(it);
            })
        } else {
            Toast.makeText(context, "Actualmente no hay red disponible!", Toast.LENGTH_SHORT).show()
        }
        return binding.root
    }

    override fun onItemClick(temp: Comics) {
        val dialogBuilder = AlertDialog.Builder(context, R.style.MyDialogTheme);
        dialogBuilder.setMessage("Desea borrar el contenido?")
        dialogBuilder.setTitle("Acción con comic")
        dialogBuilder.setPositiveButton("Borrar", object : DialogInterface.OnClickListener{
            override fun onClick(p0: DialogInterface?, p1: Int) {
                model.deleteComics(temp.nombre.toString(),temp.editorial.toString(),temp.fecha.toString(),temp.descripcion.toString())
            }
        })
        dialogBuilder.create().show();
    }
    private fun isConnectionAvailable(context: Context?): Boolean {
        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            @Suppress("DEPRECATION") val networkInfo = connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION") return networkInfo.isConnected
        }
    }
}