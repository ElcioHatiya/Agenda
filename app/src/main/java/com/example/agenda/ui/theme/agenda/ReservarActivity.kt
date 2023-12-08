package com.example.agenda.ui.theme.agenda

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.agenda.databinding.ActivityReservarBinding
import com.example.agendafatec.ui.theme.agenda.ReservaModel
import com.example.agendafatec.ui.theme.curso.CursoModel
import com.example.agendafatec.ui.theme.disciplina.DisciplinaModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

private lateinit var o:ActivityReservarBinding
private var db = Firebase.firestore
private lateinit var listaCurso:ArrayList<CursoModel>
private lateinit var listaDiscip:ArrayList<DisciplinaModel>

class ReservarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        o=ActivityReservarBinding.inflate(layoutInflater)
        setContentView(o.root)

        var CURSONOME = ""
        var DISCIPNOME = ""

        val obj = intent.getSerializableExtra("objeto") as ReservaModel
        val Nivel = intent.getSerializableExtra("nivel") as String

        var Mes = obj.mes + 1
        o.txtData.setText(obj.dia.toString()+"/"+Mes.toString())
        o.txtNomeProf.setText(obj.profNome)
        o.txtAmb.setText(obj.ambNome)

        var horario:String = when(obj.horario){

            1 -> "Horário 1 Manhã" 2 -> "Horário 2 Manhã" 3 -> "Horário 3 Manhã"
            4 -> "Horário 4 Manhã" 5 -> "Horário 5 Manhã" 6 -> "Horário 6 Manhã"
            7 -> "Horário 1 Noite" 8 -> "Horário 2 Noite" 9 -> "Horário 3 Noite"
            10 -> "Horário 4 Noite" 11 -> "Horário 5 Noite" 12 -> "Horário 6 Noite"

            else -> {""}
        }

        o.txtHora.setText(horario)

        listaCurso = arrayListOf()
        listaDiscip = arrayListOf()

        db = FirebaseFirestore.getInstance()

        db.collection("Curso").get().addOnSuccessListener {

            if (!it.isEmpty) {

                for (data in it.documents) {

                    val curso: CursoModel? = data.toObject(CursoModel::class.java)
                    if (curso != null)

                        if (Nivel == curso.nivel) listaCurso.add(curso)
                }
                o.spnCurso.adapter = ArrayAdapter(this,
                    com.example.agenda.R.layout.spinner_reservar_layout,
                    com.example.agenda.R.id.txtSpinner,listaCurso)
            }
        }.addOnFailureListener {
            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
        }

        o.spnCurso.onItemSelectedListener = object : AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {

                var curso = listaCurso[position]
                CURSONOME = curso.nome
                carregarDiscip(curso.id)
            }
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {}
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        o.spnDiscip.onItemSelectedListener = object : AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {

                var discip = listaDiscip[position]
                DISCIPNOME = discip.nome
            }
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {}
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        o.btnReservar.setOnClickListener {

            db.collection("Reserva")
            .whereEqualTo("ano", obj.ano)
            .whereEqualTo("mes", obj.mes)
            .whereEqualTo("dia", obj.dia)
            .whereEqualTo("horario", obj.horario)
            .whereEqualTo("ambID", obj.ambID)
            .whereEqualTo("disponib", "RESERVADO")
            .get().addOnSuccessListener { view ->

                if (!view.isEmpty) {

                    Toast.makeText(this, "Infelizmente esse horário foi reservado por outro professor", Toast.LENGTH_SHORT).show()

                    Handler().postDelayed({

                        finish()

                    }, 1500)

                } else {

                    Toast.makeText(this, "Reservado com sucesso!" , Toast.LENGTH_SHORT).show()

                    obj.discipNome = DISCIPNOME
                    obj.cursoNome = CURSONOME

                    obj.salvarReserva(obj)

                    Handler().postDelayed({

                        finish()

                    }, 1500)
                }
            }
        }
    }

    fun carregarDiscip(ID:String){

        db.collection("Disciplina").get().addOnSuccessListener {

            listaDiscip.clear()

            if (!it.isEmpty) {

                for (data in it.documents) {

                    val discip: DisciplinaModel? = data.toObject(DisciplinaModel::class.java)
                    if (discip != null)

                        if (ID == discip.cursoID) listaDiscip.add(discip)
                }
                o.spnDiscip.adapter = ArrayAdapter(this,
                    com.example.agenda.R.layout.spinner_reservar_layout,
                    com.example.agenda.R.id.txtSpinner, listaDiscip)
            }
        }.addOnFailureListener {
            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
        }
    }
}