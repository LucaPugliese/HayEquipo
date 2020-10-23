package com.example.tp5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.findNavController
import com.example.tp5.Objects.Partidos
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_crear_partido.*


class CrearPartido : Fragment() {

    private lateinit var v : View
    private lateinit var Edit_Horas: TextInputLayout
    //private lateinit var Txt_Horas: TextView

    private lateinit var Edit_Jugadores: TextInputLayout
   // private lateinit var Txt_Jugadores: TextView

    private lateinit var Edit_Costo: TextInputLayout
   // private lateinit var Txt_Costo: TextView

    private lateinit var Edit_Info: TextInputLayout
   // private lateinit var Txt_Info: TextView

    private lateinit var Btn_Crear: Button

    val db = Firebase.firestore





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v =  inflater.inflate(R.layout.fragment_crear_partido, container, false)

        Edit_Horas = v.findViewById(R.id.edit_horas)
       // Txt_Horas = v.findViewById(R.id.txt_horas)


        Edit_Jugadores = v.findViewById(R.id.edit_jugfaltantes)
       // Txt_Jugadores = v.findViewById(R.id.txt_jugfaltantes)

        Edit_Costo = v.findViewById(R.id.edit_costo)
       // Txt_Costo = v.findViewById(R.id.txt_costoShow)

        Edit_Info = v.findViewById(R.id.edit_info)
      //  Txt_Info = v.findViewById(R.id.txt_info)

        Btn_Crear = v.findViewById(R.id.btn_crearelpartido)


        return (v)
    }

    override fun onStart() {
        super.onStart()

        val db = FirebaseFirestore.getInstance()


        Btn_Crear.setOnClickListener {



            val horarioo = edit_horas.editText?.text.toString()
            val jugadoress = edit_jugfaltantes.editText?.text.toString()
            val costoo = edit_costo.editText?.text.toString()
            val infoo = edit_info.editText?.text.toString()
            if(horarioo.isNotBlank() and jugadoress.isNotBlank() and costoo.isNotBlank() and infoo.isNotBlank())
            {

                val partidos : Partidos = Partidos(horarioo, jugadoress, costoo.toDouble(), infoo,0.0,0.0,"", "")
                db.collection("Partidos").add(partidos)


                Snackbar.make(v, "Partido creado correctamente", Snackbar.LENGTH_LONG).show()

                v.findNavController().navigateUp()

            }

            else
            {
                Snackbar.make(v, "Por favor complete todos los campos", Snackbar.LENGTH_SHORT).show()
            }

        }






    }
}