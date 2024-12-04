package com.example.ev3iot

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ev3iot.Adapter.AdapterHistorial
import com.example.ev3iot.Models.Historial
import com.example.ev3iot.databinding.ActivityVerHistorialesBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class VerHistorialesActivity : AppCompatActivity() {

    //ViewBinding
    private lateinit var binding: ActivityVerHistorialesBinding
    //Historiales
    private lateinit var historialesList : ArrayList<Historial>
    //Adapter
    private lateinit var adapterHistorial: AdapterHistorial
    //Firebase
    private lateinit var database: DatabaseReference
    //Recicler view
    private lateinit var historialRecyclerView: RecyclerView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityVerHistorialesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        historialRecyclerView = binding.rvHistoriales
        historialRecyclerView.layoutManager = LinearLayoutManager(this)
        historialRecyclerView.hasFixedSize()

        historialesList = arrayListOf<Historial>()

        adapterHistorial = AdapterHistorial(historialesList, this)
        historialRecyclerView.adapter = adapterHistorial

        getHistorial()

    }

    private fun getHistorial() {

        database = FirebaseDatabase.getInstance().getReference("Historial")
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for (historialesSnapshot in snapshot.children){
                        val historial = historialesSnapshot.getValue(Historial::class.java)
                        historialesList.add(historial!!)
                    }
                    adapterHistorial = AdapterHistorial(historialesList, this@VerHistorialesActivity)
                    historialRecyclerView.adapter = adapterHistorial
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

}