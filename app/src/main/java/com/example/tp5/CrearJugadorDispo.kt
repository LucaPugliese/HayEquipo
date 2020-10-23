package com.example.tp5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class CrearJugadorDispo : Fragment() {

    private lateinit var v : View


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v =  inflater.inflate(R.layout.fragment_crear_jugador_dispo, container, false)
        return(v)
    }

    override fun onStart() {
        super.onStart()

    }
}