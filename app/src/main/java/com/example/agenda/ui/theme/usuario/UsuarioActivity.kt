package com.example.agenda.ui.theme.usuario

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.agenda.databinding.ActivityUsuarioBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

private lateinit var o: ActivityUsuarioBinding

private var db = Firebase.firestore
private lateinit var lista:ArrayList<UsuarioModel>

class UsuarioActivity : AppCompatActivity(), UsuarioAdapter.OnItemClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        o= ActivityUsuarioBinding.inflate(layoutInflater)
        setContentView(o.root)

        lista = arrayListOf()

        o.lstProf.layoutManager= LinearLayoutManager(this)

        db = FirebaseFirestore.getInstance()

        o.btnNovoProf.setOnClickListener {

            val prof = UsuarioModel()
            prof.nivel = "NOVO"
            val intent = Intent(this, UsuarioForm::class.java)
            intent.putExtra("objeto",prof)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        carregarLista()
    }

    private fun carregarLista(){

        lista.clear()

        db.collection("Usuario").get().addOnSuccessListener {

            if(!it.isEmpty){

                for(data in it.documents){

                    val prof:UsuarioModel? = data.toObject(UsuarioModel::class.java)
                    if(prof != null) lista.add(prof)
                }
                o.lstProf.adapter = UsuarioAdapter(lista,this)
            }
        }.addOnFailureListener {
            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onItemClick(position: Int) {

    }

    override fun onLongClick(position: Int) {

        val prof:UsuarioModel = lista[position]

        val intent = Intent(this,UsuarioForm::class.java)
        intent.putExtra("objeto",prof)
        startActivity(intent)
    }
}