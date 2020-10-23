package com.example.tp5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.findNavController
import com.example.tp5.LoginInfo.PantallaLoginDirections


class Pantalla1 : Fragment() {

    private lateinit var v : View

    private lateinit var Btn_SeleccionarPartido : Button
    private lateinit var Btn_CrearPartido : Button
    private lateinit var Btn_OfrecerseComoJugador : Button
    private lateinit var Btn_BuscarJugadoresDisponibles : Button
    private lateinit var Btn_BuscarPartidosDisponibles : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_pantalla1, container, false)

        Btn_SeleccionarPartido = v.findViewById(R.id.btn_seleccDepor)
        Btn_CrearPartido = v.findViewById(R.id.btn_crearPartido)
        Btn_OfrecerseComoJugador = v.findViewById(R.id.btn_ofrecerseJugador)
        Btn_BuscarJugadoresDisponibles = v.findViewById(R.id.btn_buscarjugadoresdispo)
        Btn_BuscarPartidosDisponibles = v.findViewById(R.id.btn_buscarpartidosdispo)


        return (v)
    }

    override fun onStart() {
        super.onStart()

        Btn_CrearPartido.setOnClickListener {

            val action = Pantalla1Directions.actionPantalla1ToCrearPartido()
            v.findNavController().navigate(action)


        }


        Btn_BuscarPartidosDisponibles.setOnClickListener {

            val action = Pantalla1Directions.actionPantalla1ToPantalla3()
            v.findNavController().navigate(action)


        }

        Btn_OfrecerseComoJugador.setOnClickListener {

            val action = Pantalla1Directions.actionPantalla1ToCrearJugadorDispo()
            v.findNavController().navigate(action)


        }





    }
}