package com.example.agendafatec.ui.theme.disciplina

import com.example.agendafatec.ui.theme.curso.CursoModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings

private lateinit var db: FirebaseFirestore

class DisciplinaModel (var id:String="", var nome:String="", var cursoID:String="", var cursoNome:String="") {

    override fun toString():String {
        return nome
    }

    fun salvarDiscip(model: DisciplinaModel) {

        db = FirebaseFirestore.getInstance()

        val doc = db.collection("Disciplina").document()

        model.id = doc.id

        doc.set(model)
    }

    fun apagarDiscip(ID: String){

        db = FirebaseFirestore.getInstance()

        db.collection("Disciplina").document(ID).delete()
    }
}