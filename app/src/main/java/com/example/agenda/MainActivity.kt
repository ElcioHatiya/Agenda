package com.example.agenda

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.agenda.databinding.ActivityMainBinding
import com.example.agenda.ui.theme.agenda.AgendaActivity
import com.example.agenda.ui.theme.usuario.AdminActivity
import com.example.agenda.ui.theme.usuario.UsuarioForm
import com.example.agenda.ui.theme.usuario.UsuarioModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

private lateinit var o:ActivityMainBinding
private var db = Firebase.firestore

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        o=ActivityMainBinding.inflate(layoutInflater)
        setContentView(o.root)

        o.chkSenha.setOnClickListener {

            if(o.chkSenha.isChecked) o.senha.inputType = 1
            else o.senha.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        }

        o.btnLogin.setOnClickListener{

            val email = o.email.text.toString()
            val senha = o.senha.text.toString()

            db.collection("Usuario")

            .whereEqualTo("email", email)
            .whereEqualTo("senha", senha)

            .get().addOnSuccessListener {

                if (!it.isEmpty) {

                    for (data in it.documents) {

                        val user: UsuarioModel? = data.toObject(UsuarioModel::class.java)
                        if (user != null) {

                            if (user.acesso == "BLOQUEADO") {

                                Toast.makeText(this,"ACESSO BLOQUEADO - Procure o Administrador",Toast.LENGTH_SHORT).show()

                            }else{

                                if(user.nivel == "ADMIN"){

                                    val intent = Intent(this, AdminActivity::class.java)
                                    intent.putExtra("objeto",user)
                                    startActivity(intent)
                                    finish()

                                }else{

                                    val intent = Intent(this, AgendaActivity::class.java)
                                    intent.putExtra("objeto",user)
                                    startActivity(intent)
                                    finish()
                                }
                            }
                        }
                    }

                }else{

                    Toast.makeText(this,"ERRO DE ACESSO",Toast.LENGTH_SHORT).show()
                }
            }.addOnFailureListener {

                Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }
}

