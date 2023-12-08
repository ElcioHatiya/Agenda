package com.example.agenda.ui.theme.disciplina

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.agenda.R
import com.example.agenda.databinding.ActivityDisciplinaBinding
import com.example.agendafatec.ui.theme.curso.CursoModel
import com.example.agendafatec.ui.theme.disciplina.DisciplinaAdapter
import com.example.agendafatec.ui.theme.disciplina.DisciplinaModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

private lateinit var o:ActivityDisciplinaBinding

private var db = Firebase.firestore
private lateinit var lista:ArrayList<DisciplinaModel>
private lateinit var ID:String

class DisciplinaActivity : AppCompatActivity() , DisciplinaAdapter.OnItemClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        o= ActivityDisciplinaBinding.inflate(layoutInflater)
        setContentView(o.root)

        o.lstDisciplina.layoutManager= LinearLayoutManager(this)

        lista = arrayListOf()

        db = FirebaseFirestore.getInstance()


        val obj = intent.getSerializableExtra("objeto") as CursoModel

        ID = obj.id

        o.txtForm.setText(obj.nome)

        o.btnNovaDiscip.setOnClickListener {

            val intent = Intent(this,DisciplinaForm::class.java)
            intent.putExtra("objeto",obj)
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

        db.collection("Disciplina").get().addOnSuccessListener {

            if(!it.isEmpty){

                lista.clear()
                for(data in it.documents){

                    val discip:DisciplinaModel? = data.toObject(DisciplinaModel::class.java)

                    if (discip != null) {
                        if(discip.cursoID == ID)  lista.add(discip)
                    }
                }
                o.lstDisciplina.adapter = DisciplinaAdapter(lista,this)
            }
        }.addOnFailureListener {
            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onItemClick(position: Int) {

    }

    override fun onLongClick(position: Int) {

        val alert = AlertDialog.Builder(this)
        alert.setMessage("Deseja excluir o resgistro? ")
            .setCancelable(true)

            .setPositiveButton("Sim") {DialogInterface, it ->

                val discip = lista[position]

                discip.apagarDiscip(discip.id)

                Toast.makeText(this, "Disciplina excluída", Toast.LENGTH_SHORT).show()

                Handler().postDelayed({

                    carregarLista()

                }, 2000)
            }
        alert.show()
    }
}