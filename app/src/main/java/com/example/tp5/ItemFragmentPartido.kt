package com.example.tp5

import android.os.Bundle
import android.provider.Telephony
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.tp5.Objects.Deportes
import com.example.tp5.Objects.Partidos
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Text


class ItemFragmentPartido : Fragment() {

    lateinit var v : View

    private lateinit var Txt_horarios: TextView
    private lateinit var Txt_jugadores: TextView
    private lateinit var Txt_costo: TextView
    private lateinit var Txt_des: TextView

    private lateinit var Btn_QuieroAcceder : Button

    var Lista : MutableList<Deportes> = ArrayList<Deportes>()

    var position = 0

    private val db = Firebase.firestore


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v =  inflater.inflate(R.layout.fragment_item_partido, container, false)

        Txt_horarios = v.findViewById(R.id.txt_horarioShow)
        Txt_jugadores = v.findViewById(R.id.txt_jugadoresShow)
        Txt_costo = v.findViewById(R.id.txt_costoShow)
        Txt_des = v.findViewById(R.id.txt_desShow)

        Btn_QuieroAcceder = v.findViewById(R.id.btn_aceptarPartido)

        return (v)
    }

    override fun onStart() {
        super.onStart()

        var horario = ItemFragmentPartidoArgs.fromBundle(requireArguments()).horario

        var docRef = db.collection("Partidos")
        docRef.whereEqualTo("horario", horario ).get()
            .addOnSuccessListener { dataSnapshot ->
                if (dataSnapshot != null) {
                    var partidosDataSnapshot = dataSnapshot.documents
                    var partido = partidosDataSnapshot[0].toObject<Deportes>()
                    if (partido != null) {
                        Txt_horarios.text = partido.horario
                        Txt_jugadores.text = partido.cantidad_jugadores
                        Txt_costo.text = partido.costo.toString()
                        Txt_des.text = partido.descripcion
                        //Glide.with(this).load(partido.imagen).into(img_emp)
                    }
                } else {
                    Log.d("Test", "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("Test", "get failed with ", exception)
            }



    }
}