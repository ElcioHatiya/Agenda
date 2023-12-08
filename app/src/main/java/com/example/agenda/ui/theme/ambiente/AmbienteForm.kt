package com.example.agenda.ui.theme.ambiente

import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.example.agenda.R
import com.example.agenda.databinding.ActivityAmbienteFormBinding
import com.example.agendafatec.ui.theme.ambiente.AmbienteModel
import com.google.android.material.snackbar.Snackbar

private lateinit var o:ActivityAmbienteFormBinding
private var msg = arrayOf("Informe o nome do novo Ambiente",
    "Ambiente cadastrado com sucesso!",
    "Ambiente atualizado com sucesso!",
    "Ambiente excluído")

class AmbienteForm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        o= ActivityAmbienteFormBinding.inflate(layoutInflater)
        setContentView(o.root)

        o.progressBar.visibility = View.INVISIBLE
        o.chkPublico.isChecked = true

        val obj = intent.getSerializableExtra("objeto") as AmbienteModel

        if(obj.nivel == "NOVO") {

            o.btnExcluir.visibility = View.INVISIBLE

        }else{

            o.txtForm.setText("Editar " + obj.nome)

            o.nome.setText(obj.nome)

            when(obj.nivel){

                "FATEC" -> o.chkFATEC.isChecked = true

                "ETEC" -> o.chkETEC.isChecked = true

                else -> { o.chkPublico.isChecked = true }
            }

            if(obj.acesso == "BLOQUEADO") o.chkUso.isChecked = true
        }

        fun progressBar(){

            o.progressBar.visibility= View.VISIBLE
            o.progressBar.max=1000
            ObjectAnimator.ofInt(o.progressBar,"progress",1000)
                .setDuration(1000)
                .start()
        }

        o.btnExcluir.setOnClickListener {view->

            val alert = AlertDialog.Builder(this)
            alert.setMessage("Deseja excluir o resgistro? ")
                .setCancelable(true)

                .setPositiveButton("Sim") {DialogInterface, it ->

                    val amb = AmbienteModel()

                    amb.apagar(obj.id)

                    progressBar()

                    Handler().postDelayed({

                        val snackbar = Snackbar.make(view,msg[3], Snackbar.LENGTH_INDEFINITE)
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

        o.btnSalvar.setOnClickListener {

            if(o.nome.text.toString().isEmpty()){

                val snackBar = Snackbar.make(it, msg[0], Snackbar.LENGTH_SHORT)
                snackBar.setBackgroundTint(resources.getColor(R.color.snackAmarelo))
                snackBar.setTextColor(resources.getColor(R.color.black))
                snackBar.show()

            }else{

                var Acesso: String
                var Nivel: String
                if(o.chkFATEC.isChecked) Nivel = "FATEC" else if (o.chkETEC.isChecked) Nivel = "ETEC" else Nivel = "PUBLICO"
                if(o.chkUso.isChecked) Acesso="BLOQUEADO" else Acesso = "ATIVO"

                val amb = AmbienteModel()

                with(amb){

                    nome = o.nome.text.toString()
                    acesso = Acesso
                    nivel = Nivel
                }

                if(obj.nivel == "NOVO"){

                    amb.salvarAMB(amb)

                    progressBar()

                    Handler().postDelayed({

                        val snackbar = Snackbar.make(it,msg[1], Snackbar.LENGTH_INDEFINITE)
                        snackbar.setBackgroundTint(resources.getColor(R.color.snackVerde))
                        snackbar.show()

                    }, 1500)

                    Handler().postDelayed({

                        finish()

                    }, 3500)

                }else{

                    amb.id=obj.id
                    amb.atualizarAMB(amb,obj.id)

                    progressBar()

                    Handler().postDelayed({

                        val snackbar = Snackbar.make(it,msg[2], Snackbar.LENGTH_INDEFINITE)
                        snackbar.setBackgroundTint(resources.getColor(R.color.snackVerde))
                        snackbar.show()

                    }, 1500)

                    Handler().postDelayed({

                        finish()

                    }, 3500)
                }
            }
        }
    }
}