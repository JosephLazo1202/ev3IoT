package com.example.ev3iot.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ev3iot.HistorialActivity
import com.example.ev3iot.Models.Historial
import com.example.ev3iot.R

class AdapterHistorial(private var historiales: ArrayList<Historial>, private val context: Context) :
    RecyclerView.Adapter<AdapterHistorial.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val usuario: TextView = itemView.findViewById(R.id.tvUsuario)
        val accion: TextView = itemView.findViewById(R.id.tvAccion)
        val fecha: TextView = itemView.findViewById(R.id.tvFecha)
        val btnVolver: Button = itemView.findViewById(R.id.btnVolver)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_historiales, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return historiales.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val historial = historiales[position]

        holder.usuario.text = historial.usuario
        holder.accion.text = historial.accion
        holder.fecha.text = historial.fecha

        holder.btnVolver.setOnClickListener {
            val intent = Intent(context, HistorialActivity::class.java)
            context.startActivity(intent)
        }
    }

}