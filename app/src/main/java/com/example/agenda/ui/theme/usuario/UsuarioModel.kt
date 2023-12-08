package com.example.agenda.ui.theme.usuario

import android.widget.Toast
import com.example.agenda.ui.theme.ambiente.AmbienteActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.QuerySnapshot
import java.io.Serializable

private lateinit var db: FirebaseFirestore

class UsuarioModel (var id:String="", var nome:String="", var email:String="", var senha:String="",
var telefone:String="", var nivel:String="", var acesso:String=""): Serializable


fun salvar(model: UsuarioModel){

    db= FirebaseFirestore.getInstance()

    db.firestoreSettings= FirebaseFirestoreSettings.Builder().build()

    val doc = db.collection("Usuario").document()

    model.id = doc.id

    doc.set(model)
}

fun atualizar(ID:String , model:UsuarioModel){

    db= FirebaseFirestore.getInstance()

    db.firestoreSettings = FirebaseFirestoreSettings.Builder().build()

    val doc = db.collection("Usuario").document(ID)

    doc.set(model)
}

fun apagar(ID: String){

    db = FirebaseFirestore.getInstance()

    db.firestoreSettings= FirebaseFirestoreSettings.Builder().build()

    db.collection("Usuario").document(ID).delete()
}