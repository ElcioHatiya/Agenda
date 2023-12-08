package com.example.agenda.ui.theme.disciplina

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.example.agenda.R
import com.example.agenda.databinding.ActivityDisciplinaFormBinding
import com.example.agendafatec.ui.theme.curso.CursoModel
import com.example.agendafatec.ui.theme.disciplina.DisciplinaModel
import com.google.android.material.snackbar.Snackbar

private lateinit var o:ActivityDisciplinaFormBinding

class DisciplinaForm : AppCompatActivity() {

    private lateinit var o: ActivityDisciplinaFormBinding
    private var msg = arrayOf("Informe o nome da nova Disciplina",
        "Disciplina cadastrada com sucesso!")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        o=ActivityDisciplinaFormBinding.inflate(layoutInflater)
        setContentView(o.root)

        val obj = intent.getSerializableExtra("objeto") as CursoModel

        o.txtForm.setText(obj.nome)

        fun progressBar(){

            o.progressBar.visibility= View.VISIBLE
            o.progressBar.max=1000
            ObjectAnimator.ofInt(o.progressBar,"progress",1000)
                .setDuration(1500)
                .start()
        }

        o.btnSalvar.setOnClickListener {

            if(o.nome.text.toString().isEmpty()) {

                val snackBar = Snackbar.make(it, msg[0], Snackbar.LENGTH_SHORT)
                snackBar.setBackgroundTint(resources.getColor(R.color.snackAmarelo))
                snackBar.setTextColor(resources.getColor(R.color.black))
                snackBar.show()

            }else{

                val discip = DisciplinaModel()

                discip.nome = o.nome.text.toString()
                discip.cursoID = obj.id
                discip.cursoNome = obj.nome

                discip.salvarDiscip(discip)

                progressBar()

                Handler().postDelayed({

                    val snackbar = Snackbar.make(it,msg[1], Snackbar.LENGTH_INDEFINITE)
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