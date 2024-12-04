package com.example.ev3iot

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ev3iot.databinding.ActivityLoginBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class LoginActivity : AppCompatActivity() {
    //Configuración de viewBinding
    private lateinit var binding: ActivityLoginBinding

    //Configurar firebase
    private lateinit var auth : FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //Inicializar viewbinding
        binding = ActivityLoginBinding.inflate((layoutInflater))
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //Inicializar Firebase Auth
        auth = Firebase.auth
        //Programar el boton de login
        binding.btninicioSesion.setOnClickListener{
            val correo = binding.correoElectronico.text.toString()
            val password = binding.contra.text.toString()

            if(correo.isEmpty()){
                binding.correoElectronico.error = "Porfavor ingrese un correo"
                return@setOnClickListener
            }

            if (password.isEmpty()){
                binding.contra.error = "Porfavor ingresa una contraseña valida"
                return@setOnClickListener
            }

            signIn(correo,password)
        }
        binding.btnregistrarse1.setOnClickListener {
            val intent = Intent(this, RegistrarActivity::class.java)
            startActivity(intent)
        }
    }
    private fun signIn(correo: String, password: String) {
        auth.signInWithEmailAndPassword(correo,password)
            .addOnCompleteListener{
                if(it.isSuccessful) {
                    Toast.makeText(this, "Inicio de sesión correcto", Toast.LENGTH_SHORT).show()
                    //Ir a la siguiente pantalla
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this,"ERROR al iniciar sesión", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
