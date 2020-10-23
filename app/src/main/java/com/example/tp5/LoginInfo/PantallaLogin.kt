package com.example.tp5.LoginInfo

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.findNavController
import com.example.tp5.MainActivity
import com.example.tp5.R
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main_login.*
import kotlinx.android.synthetic.main.fragment_crear_partido.*
import kotlinx.android.synthetic.main.fragment_pantalla_login.*



class PantallaLogin : Fragment() {

    private lateinit var v : View

    private lateinit var InputEmailLay : TextInputLayout
    private lateinit var InputContraLay : TextInputLayout
    private lateinit var BotonLogin : Button
    private lateinit var BotonCrearUsu : Button

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_pantalla_login, container, false)

        BotonLogin = v.findViewById(R.id.btn_login)
        BotonCrearUsu = v.findViewById(R.id.btn_crearusu)
        InputEmailLay = v.findViewById(R.id.edit_usu)
        InputContraLay = v.findViewById(R.id.edit_contra)

        auth = FirebaseAuth.getInstance()



        return (v)
    }

    override fun onStart() {
        super.onStart()



        val InputContra : EditText? = InputContraLay.editText
        val InputEmail : EditText? = InputEmailLay.editText

        val currentUser = auth.currentUser

        BotonCrearUsu.setOnClickListener {

            val action = PantallaLoginDirections.actionPantallaLoginToPantallaRegistro()
            v.findNavController().navigate(action)


        }

        BotonLogin.setOnClickListener {
            if (InputEmail != null) {
                if (InputContra != null) {
                    if(InputEmail.text.isNotBlank() and InputContra.text.isNotBlank()) {
                        val username = InputEmail.text.toString()
                        val password = InputContra.text.toString()

                        Snackbar.make(v, "Cargando", Snackbar.LENGTH_LONG).show()

                        auth.signInWithEmailAndPassword(username, password)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    val user = auth.currentUser
                                    Log.d(ContentValues.TAG, "signInWithEmail:success - user:${user?.email}")

                                    Snackbar.make(v, "Logeado correcto", Snackbar.LENGTH_SHORT).show()


                                    val intent = Intent(context, MainActivity::class.java)
                                    startActivity(intent)
                                    activity?.finish()

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(ContentValues.TAG, "signInWithEmail:failure", task.exception)
                                    Snackbar.make(v, "Cuenta no registrada", Snackbar.LENGTH_SHORT).show()

                                }
                            }
                    } else {
                        Snackbar.make(v, "Por favor, complete todos los campos", Snackbar.LENGTH_SHORT).show()
                    }
                }
            }
        }



    }
}