package com.example.tp5

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp5.Objects.Deportes
import com.example.tp5.Objects.Partidos
import com.example.tp5.adapters.deportesListAdapter
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase


class Pantalla3 : Fragment() {

    lateinit var v : View
    lateinit var recDeportes: RecyclerView
    var deportes : MutableList<Deportes> = ArrayList<Deportes>()
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var deportesListAdapter: deportesListAdapter

    private val db = Firebase.firestore


    companion object {
        fun newInstance() = Fragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_pantalla3, container, false)
        recDeportes = v.findViewById(R.id.rec_deportes)


        return (v)

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    override fun onStart() {
        super.onStart()

        deportes = ArrayList<Deportes>()
        var docRef = db.collection("Partidos")
        docRef.get()
            .addOnSuccessListener { dataSnapshot ->
                if (dataSnapshot != null) {
                    var partidoDataSnapshot = dataSnapshot.documents
                    for (snapshot in partidoDataSnapshot) {
                        snapshot.toObject<Deportes>()?.let { deportes.add(it) }
                    }
                    recDeportes.setHasFixedSize(true)

                    linearLayoutManager = LinearLayoutManager(context)
                    recDeportes.layoutManager = linearLayoutManager

                    deportesListAdapter = deportesListAdapter(deportes,requireContext()){position -> onItemClick(position)}

                    recDeportes.adapter = deportesListAdapter
                } else {
                    Log.d("Test", "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("Test", "get failed with ", exception)
            }




    }

    fun onItemClick (position : Int) {
        val toscreen2 = Pantalla3Directions.actionPantalla3ToItemFragmentPartido(deportes[position].horario)
        v.findNavController().navigate(toscreen2)
    }
}