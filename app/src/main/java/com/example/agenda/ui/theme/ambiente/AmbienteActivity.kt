package com.example.agenda.ui.theme.ambiente

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.agenda.databinding.ActivityAmbienteBinding
import com.example.agendafatec.ui.theme.ambiente.AmbienteAdapter
import com.example.agendafatec.ui.theme.ambiente.AmbienteModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

private lateinit var o:ActivityAmbienteBinding
private var db = Firebase.firestore
private lateinit var lista:ArrayList<AmbienteModel>

class AmbienteActivity : AppCompatActivity(), AmbienteAdapter.OnItemClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        o=ActivityAmbienteBinding.inflate(layoutInflater)
        setContentView(o.root)

        o.lstAmbientes.layoutManager= LinearLayoutManager(this)

        db = FirebaseFirestore.getInstance()

        o.btnNovoAmb.setOnClickListener {

            val amb = AmbienteModel()
            amb.nivel = "NOVO"
            val intent = Intent(this, AmbienteForm::class.java)
            intent.putExtra("objeto",amb)
            startActivity(intent)
        }

    }

    private fun carregarLista(){

        db.collection("Ambiente").get().addOnSuccessListener {

            lista = arrayListOf()

            if(!it.isEmpty){

                for(data in it.documents){

                    val amb:AmbienteModel? = data.toObject(AmbienteModel::class.java)
                    if(amb != null) lista.add(amb)
                }
                o.lstAmbientes.adapter = AmbienteAdapter(lista,this)
            }
        }.addOnFailureListener {
            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
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
    override fun onItemClick(position: Int) {

    }

    override fun onLongClick(position: Int) {

        val amb:AmbienteModel = lista[position]

        val intent = Intent(this,AmbienteForm::class.java)
        intent.putExtra("objeto",amb)
        startActivity(intent)
    }
}