package com.example.agenda.ui.theme.curso

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.agenda.R
import com.example.agenda.databinding.ActivityCursoBinding
import com.example.agenda.ui.theme.disciplina.DisciplinaActivity
import com.example.agendafatec.ui.theme.curso.CursoAdapter
import com.example.agendafatec.ui.theme.curso.CursoModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

private lateinit var o:ActivityCursoBinding
private var db = Firebase.firestore
private lateinit var lista:ArrayList<CursoModel>

class CursoActivity : AppCompatActivity(), CursoAdapter.OnItemClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        o=ActivityCursoBinding.inflate(layoutInflater)
        setContentView(o.root)

        o.lstCurso.layoutManager= LinearLayoutManager(this)

        lista = arrayListOf()

        db = FirebaseFirestore.getInstance()

        o.btnNovoCurso.setOnClickListener {

            val curso = CursoModel()
            curso.nivel = "NOVO"
            val intent = Intent(this, CursoForm::class.java)
            intent.putExtra("objeto",curso)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        carregarLista()
    }

    override fun onResume() {
        super.onResume()
        carregarLista()
    }

    private fun carregarLista(){

        db.collection("Curso").get().addOnSuccessListener {

            lista.clear()

            if(!it.isEmpty){

                for(data in it.documents){

                    val curso:CursoModel? = data.toObject(CursoModel::class.java)
                    if(curso != null) lista.add(curso)
                }
                o.lstCurso.adapter = CursoAdapter(lista,this)
            }
        }.addOnFailureListener {
            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onItemClick(position: Int) {

    }

    override fun onLongClick(position: Int) {

        val curso:CursoModel = lista[position]

        val alert = AlertDialog.Builder(this)
        alert.setMessage("O que deseja fazer com "+curso.nome.toString()+" ?")
        .setCancelable(true)

        .setPositiveButton("Editar dados"){DialogInterface,it->

            val intent = Intent(this,CursoForm::class.java)
            intent.putExtra("objeto",curso)
            startActivity(intent)
        }
        .setNegativeButton("Editar DISCIPLINAS"){DialogInterface,it->

            val intent = Intent(this, DisciplinaActivity::class.java)
            intent.putExtra("objeto",curso)
            startActivity(intent)
        }
        .show()
    }
}