package com.example.agenda.ui.theme.curso

import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.example.agenda.R
import com.example.agenda.databinding.ActivityCursoFormBinding
import com.example.agenda.ui.theme.disciplina.DisciplinaActivity
import com.example.agenda.ui.theme.usuario.AdminActivity
import com.example.agendafatec.ui.theme.curso.CursoModel
import com.google.android.material.snackbar.Snackbar

private lateinit var o:ActivityCursoFormBinding

class CursoForm : AppCompatActivity() {

    private var msg = arrayOf("Informe o nome do novo curso",
        "Curso cadastrado com sucesso!",
        "Curso atualizado com sucesso!",
        "Curso excluído")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        o= ActivityCursoFormBinding.inflate(layoutInflater)
        setContentView(o.root)

        o.progressBar.visibility = View.INVISIBLE
        o.chkETEC.isChecked = true

        val obj = intent.getSerializableExtra("objeto") as CursoModel

        if(obj.nivel == "NOVO") {

            o.btnExcluir.visibility = View.INVISIBLE

        }else{

            o.txtForm.setText("Editar " + obj.nome)
            o.nome.setText(obj.nome)
            if(obj.nivel == "FATEC") o.chkFATEC.isChecked = true
            else o.chkETEC.isChecked = true
        }

        fun progressBar(){

            o.progressBar.visibility= View.VISIBLE
            o.progressBar.max=1000
            ObjectAnimator.ofInt(o.progressBar,"progress",1000)
                .setDuration(1500)
                .start()
        }

        o.btnExcluir.setOnClickListener {view->

            val alert = AlertDialog.Builder(this)
            alert.setMessage("Deseja excluir o resgistro? ")
                .setCancelable(true)

                .setPositiveButton("Sim") {DialogInterface, it ->

                    val curso = CursoModel()

                    curso.apagarCurso(obj.id)

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

            if(o.nome.text.toString().isEmpty()) {

                val snackBar = Snackbar.make(it, msg[0], Snackbar.LENGTH_SHORT)
                snackBar.setBackgroundTint(resources.getColor(R.color.snackAmarelo))
                snackBar.setTextColor(resources.getColor(R.color.black))
                snackBar.show()

            }else{

                var Nivel: String
                if(o.chkFATEC.isChecked) Nivel = "FATEC" else  Nivel = "ETEC"

                val curso = CursoModel()

                curso.nome = o.nome.text.toString()
                curso.nivel = Nivel

                if(obj.nivel == "NOVO") {

                    curso.salvarCurso(curso)

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

                    curso.id=obj.id
                    curso.atualizarCurso(curso,obj.id)

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