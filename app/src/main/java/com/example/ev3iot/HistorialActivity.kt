package com.example.ev3iot

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ev3iot.Models.Historial
import com.example.ev3iot.databinding.ActivityHistorialBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class HistorialActivity : AppCompatActivity() {

    //Activar firebase
    private lateinit var database: DatabaseReference

    //Inicializar binding
    private lateinit var binding: ActivityHistorialBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        //Configurar viewBinding
        binding = ActivityHistorialBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Iniciar la base de datos
        database = FirebaseDatabase.getInstance().getReference("Historial")

        binding.btnAgregar.setOnClickListener {
            val usuario = binding.etUsuario.text.toString()
            val accion = binding.etAccion.text.toString()
            val fecha = binding.etFecha.text.toString()
            val id = database.child("Historial").push().key

            if(usuario.isEmpty()){
                binding.etUsuario.error = "Por favor ingresar usuario"
                return@setOnClickListener
            }

            if(accion.isEmpty()){
                binding.etAccion.error = "Por favor ingresar una acción"
                return@setOnClickListener
            }
            if(fecha.isEmpty()){
                binding.etFecha.error = "Por favor ingresar una fecha"
                return@setOnClickListener
            }

            val historial = Historial(id, usuario, accion, fecha)

            database.child(id!!).setValue(historial)
                .addOnSuccessListener {
                    binding.etFecha.setText("")
                    binding.etAccion.setText("")
                    binding.etUsuario.setText("")
                    Snackbar.make(binding.root, "Historial Agregado", Snackbar.LENGTH_SHORT)
                        .show()
                }

        }

        binding.btnVolver.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.btnVer.setOnClickListener {
            val intent = Intent(this, VerHistorialesActivity::class.java)
            startActivity(intent)
        }

    }
}