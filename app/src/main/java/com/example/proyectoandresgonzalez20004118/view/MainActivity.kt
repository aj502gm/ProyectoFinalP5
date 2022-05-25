package com.example.proyectoandresgonzalez20004118.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.proyectoandresgonzalez20004118.R
import com.example.proyectoandresgonzalez20004118.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var binding: ActivityMainBinding
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configureDrawer()
    }
    fun configureDrawer(){
        setSupportActionBar(binding.toolBar)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.navView.setupWithNavController(navController)
        toggle = ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolBar, R.string.app_name, R.string.app_name)
        binding.drawerLayout.addDrawerListener(toggle)
        //toggle.drawerArrowDrawable.setColor()
        binding.navView.setNavigationItemSelectedListener(this)
        toggle.syncState()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        if(item.itemId == R.id.item_registro){
            Log.d("ALGOOOOO", "ENTRAAAA")
            navController.navigate(R.id.insertFragment)
        }else if(item.itemId == R.id.item_listado){
            navController.navigate(R.id.listFragment)
        }else if(item.itemId == R.id.item_about){
            navController.navigate(R.id.thirdFragment)
        }else{
            finish();
            System.exit(0);
        }
        //return super.onOptionsItemSelected(item)
        binding.drawerLayout.closeDrawers();
        return true
    }
}

