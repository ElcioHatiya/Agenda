package com.example.agenda.ui.theme.usuario

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.InputType
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.agenda.R
import com.example.agenda.databinding.ActivityUsuarioFormBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

private lateinit var o:ActivityUsuarioFormBinding
private var db = Firebase.firestore
lateinit var ID:String
private var msg = arrayOf("Preencha todos os campos!",
    "Usuário cadastrado com sucesso!",
    "Usuário atualizado com sucesso!",
    "Usuário excluído",
    "Este Email já está sendo utilizado")

class UsuarioForm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        o=ActivityUsuarioFormBinding.inflate(layoutInflater)
        setContentView(o.root)

        o.chkAtivo.isChecked = true
        o.chkETEC.isChecked = true
        o.progressBar.visibility = View.INVISIBLE

        val obj = intent.getSerializableExtra("objeto") as UsuarioModel

        if(obj.nivel == "NOVO") {

            o.btnExcluir.visibility = View.INVISIBLE

        }else{

            o.email.isEnabled = false
            o.email.setTextColor(resources.getColor(R.color.Cinza))

            o.txtForm.setText("Editar " + obj.nome)

            o.email.setText(obj.email)
            o.nome.setText(obj.nome)
            o.senha.setText(obj.senha)
            o.telefone.setText(obj.telefone)
            ID = obj.id

            when(obj.nivel){

                "FATEC" -> o.chkFATEC.isChecked = true

                "ETEC" -> o.chkETEC.isChecked = true

                "ADMIN" -> o.chkAdmin.isChecked = true

                else -> { o.chkAluno.isChecked = true }
            }

            if(obj.acesso == "ATIVO") o.chkAtivo.isChecked = true

            else o.chkBloqueado.isChecked = true
        }

        o.chkSenha.setOnClickListener {

            if(o.chkSenha.isChecked) o.senha.inputType = 1
            else o.senha.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD

        }

        o.btnExcluir.setOnClickListener {view->

            val alert = AlertDialog.Builder(this)
            alert.setMessage("Deseja excluir o resgistro?")
                .setCancelable(true)

                .setPositiveButton("Sim") {DialogInterface, it ->

                    apagar(ID)

                    Handler().postDelayed({

                        val snackbar = Snackbar.make(view,msg[3],Snackbar.LENGTH_INDEFINITE)
                        snackbar.setBackgroundTint(resources.getColor(R.color.snackAmarelo))
                        snackbar.setTextColor(resources.getColor(R.color.black))
                        snackbar.show()

                    }, 1500)

                    Handler().postDelayed({

                        finish()

                    }, 3500)
                }
            alert.show()
        }

        o.btnSalvar.setOnClickListener {view->

            if(o.email.text.toString().isEmpty()||
                o.nome.text.toString().isEmpty()||
                o.senha.text.toString().isEmpty()){

                val snackBar = Snackbar.make(view, msg[0], Snackbar.LENGTH_SHORT)
                snackBar.setBackgroundTint(resources.getColor(R.color.snackAmarelo))
                snackBar.setTextColor(resources.getColor(R.color.black))
                snackBar.show()

            }else{

                var Nivel: String
                if(o.chkFATEC.isChecked) Nivel = "FATEC"
                else if (o.chkETEC.isChecked) Nivel = "ETEC"
                else if (o.chkAluno.isChecked) Nivel = "ALUNO"
                else Nivel = "ADMIN"

                var Acesso: String
                if(o.chkBloqueado.isChecked) Acesso="BLOQUEADO" else Acesso = "ATIVO"

                var user = UsuarioModel()

                with(user){

                    email = o.email.text.toString()
                    nome = o.nome.text.toString()
                    senha = o.senha.text.toString()
                    telefone = o.telefone.text.toString()
                    acesso = Acesso
                    nivel = Nivel
                }

                if(obj.nivel == "NOVO"){

                    db.collection("Usuario")
                    .whereEqualTo("email", o.email.text.toString())
                    .get().addOnSuccessListener { view ->

                        if (!view.isEmpty) {   // se encontrar email cadastradado, não resgistra

                            o.email.setText("")
                            Toast.makeText(this, msg[4], Toast.LENGTH_SHORT).show()

                        } else {

                            salvar(user)

                            Toast.makeText(this, msg[1], Toast.LENGTH_SHORT).show()

                            Handler().postDelayed({

                                finish()

                            }, 1500)
                        }
                    }

                }else{

                    user.id = ID
                    atualizar(ID,user)

                    val snackbar = Snackbar.make(view,msg[2],Snackbar.LENGTH_INDEFINITE)
                    snackbar.setBackgroundTint(resources.getColor(R.color.snackVerde))
                    snackbar.show()

                    Handler().postDelayed({

                        finish()

                    }, 1500)
                }
            }
        }
    }
}