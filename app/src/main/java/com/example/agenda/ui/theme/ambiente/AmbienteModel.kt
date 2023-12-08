package com.example.agendafatec.ui.theme.ambiente

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import java.io.Serializable

private lateinit var db: FirebaseFirestore

class AmbienteModel (var nome:String="", var id:String="", var acesso:String="", var nivel:String=""):Serializable {

    override fun toString():String {
        return nome
    }

    fun salvarAMB(model:AmbienteModel) {

        db = FirebaseFirestore.getInstance()

        db.firestoreSettings = FirebaseFirestoreSettings.Builder().build()

        val doc = db.collection("Ambiente").document()

        model.id = doc.id

        doc.set(model)
    }

    fun atualizarAMB(model:AmbienteModel,ID:String) {

        db = FirebaseFirestore.getInstance()

        val doc = db.collection("Ambiente").document(ID)

        doc.set(model)
    }

    fun apagar(ID: String){

        db = FirebaseFirestore.getInstance()

        db.firestoreSettings = FirebaseFirestoreSettings.Builder().build()

        db.collection("Ambiente").document(ID).delete()
    }
}