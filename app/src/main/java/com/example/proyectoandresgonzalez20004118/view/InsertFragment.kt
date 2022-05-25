package com.example.proyectoandresgonzalez20004118.view

import android.content.Context
import android.net.NetworkCapabilities
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.proyectoandresgonzalez20004118.R
import com.example.proyectoandresgonzalez20004118.databinding.FragmentInsertBinding
import com.example.proyectoandresgonzalez20004118.model.Comics
import com.example.proyectoandresgonzalez20004118.viewmodel.FormularioViewModel

class InsertFragment : Fragment() {
    lateinit var binding: FragmentInsertBinding
    lateinit var model: FormularioViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        model = ViewModelProvider(this).get(FormularioViewModel::class.java)
        binding = FragmentInsertBinding.inflate(layoutInflater, container, false)

        binding.btnGrabar.setOnClickListener{
            Log.d("Nombre", binding.txtNombre.text.toString())
            if(isConnectionAvailable(context)){
                model.uploadComic(binding.txtNombre.text.toString(),binding.spTipo.selectedItem.toString(),binding.txtFecha.text.toString(),binding.txtDescripcion.text.toString())
            }else{
                Toast.makeText(context, "Actualmente no hay red disponible!", Toast.LENGTH_SHORT).show()
            }
            model.mostrarMensaje.observe(viewLifecycleOwner,{
                if(!it.equals("")){
                   Toast.makeText(context, it, Toast.LENGTH_LONG ).show()
                    model.mostrarMensaje.value = ""
                }else clearTxt()
            })
        }

        return binding.root
    }

    private fun clearTxt(){
        binding.txtNombre.text.clear()
        binding.txtFecha.text.clear()
        binding.txtDescripcion.text.clear()
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