package com.example.tp5.LoginInfo

import android.content.ContentValues
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.findNavController
import com.example.tp5.R
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest



class PantallaRegistro : Fragment() {

    private lateinit var v : View
    private lateinit var R_Usuario : TextInputLayout
    private lateinit var R_EmailLay : TextInputLayout
    private lateinit var R_ContraLay : TextInputLayout
    private lateinit var R_ConfirmContraLay : TextInputLayout
    private lateinit var R_Crear : Button
    private lateinit var R_inicioBack : Button

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_pantalla_registro, container, false)

        R_Usuario = v.findViewById(R.id.edit_nombreusu)
        R_EmailLay = v.findViewById(R.id.edit_email)
        R_ContraLay = v.findViewById(R.id.edit_pass)
        R_ConfirmContraLay = v.findViewById(R.id.edit_pass2)
        R_Crear = v.findViewById(R.id.btn_register)
        R_inicioBack = v.findViewById(R.id.btn_loginBack)


        auth = FirebaseAuth.getInstance()



        return (v)
    }

    override fun onStart() {
        super.onStart()



        val R_Email : EditText? = R_EmailLay.editText
        val R_Contra : EditText? = R_ContraLay.editText
        val R_ConfirmContra : EditText? = R_ConfirmContraLay.editText



        R_inicioBack.setOnClickListener {

            val action = PantallaRegistroDirections.actionPantallaRegistroToPantallaLogin()

            v.findNavController().navigate(action)


        }




        R_Crear.setOnClickListener {
            if (R_Email != null) {
                if (R_Contra != null) {
                    if(R_Email.text.isNotBlank() and R_Contra.text.isNotBlank()) {
                        val username = R_Email.text.toString()
                        val password = R_Contra.text.toString()
                        val passwordCheck = R_ConfirmContra?.text.toString()

                        if(password == passwordCheck) {
                            auth.createUserWithEmailAndPassword(username, password)
                                .addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d(ContentValues.TAG, "createUserWithEmail:success")


                                        val user = auth.currentUser


                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w(ContentValues.TAG, "createUserWithEmail:failure", task.exception)
                                        Snackbar.make(v, "Authentication failed.", Snackbar.LENGTH_SHORT).show()
                                    }
                                }
                            Snackbar.make(v, "Cuenta creada", Snackbar.LENGTH_SHORT).show()
                            v.findNavController().navigateUp()
                        } else {
                            Snackbar.make(v, "Las contraseñas no coinciden", Snackbar.LENGTH_SHORT).show()
                        }
                    } else {
                        Snackbar.make(v, "Por favor, complete todos los campos", Snackbar.LENGTH_SHORT).show()
                    }
                }
            }
        }


    }
}