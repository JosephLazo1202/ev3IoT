package com.example.ev3iot

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ev3iot.Vistas.AcercaFragment
import com.example.ev3iot.Vistas.ConfiguracionFragment
import com.example.ev3iot.Vistas.InicioFragment
import com.example.ev3iot.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, InicioFragment()).commit()
        }

        binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.item1 -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container, InicioFragment()).commit()
                    true
                }
                R.id.item2 -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container, AcercaFragment()).commit()
                    true
                }
                R.id.item3 -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container, ConfiguracionFragment()).commit()
                    true
                }
                else -> false
            }
        }

        binding.bottomNavigation.setOnItemReselectedListener {
            when(it.itemId){
                R.id.item1 -> {
                    true
                }
                R.id.item2 -> {
                    true
                }
                R.id.item3 -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container, ConfiguracionFragment()).commit()
                    true
                }
                else -> false
            }
        }

    }
}