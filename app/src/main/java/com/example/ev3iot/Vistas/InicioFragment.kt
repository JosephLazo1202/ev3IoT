package com.example.ev3iot.Vistas

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.ev3iot.HistorialActivity
import com.example.ev3iot.R
import com.example.ev3iot.databinding.FragmentInicioBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [InicioFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InicioFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    //Inicializar viewBinding
    private var _binding: FragmentInicioBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Configuracion de viewBinding
        _binding = FragmentInicioBinding.inflate(inflater, container, false)
        val view = binding.root

        //Cambiar pantalla CRUD
        binding.btnHistorial.setOnClickListener {
            val intent = Intent(requireContext(), HistorialActivity::class.java)
            startActivity(intent)
        }
        binding.btnAbrir.setOnClickListener {
            Toast.makeText(requireContext(), "Se abrió el portón", Toast.LENGTH_SHORT).show()
        }
        binding.btnCerrar.setOnClickListener {
            Toast.makeText(requireContext(), "Se cerró el portón", Toast.LENGTH_SHORT).show()
        }
        return  view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment InicioFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            InicioFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}