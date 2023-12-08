package com.example.agendafatec.ui.theme.curso

import android.widget.Toast
import com.example.agendafatec.ui.theme.disciplina.DisciplinaModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import java.io.Serializable

private lateinit var db: FirebaseFirestore
class CursoModel (var nome:String="", var id:String="", var nivel:String=""):Serializable {

    override fun toString():String {
        return nome
    }

    fun salvarCurso(model: CursoModel) {

        db = FirebaseFirestore.getInstance()

        val doc = db.collection("Curso").document()

        model.id = doc.id

        doc.set(model)
    }

    fun atualizarCurso(model: CursoModel, ID:String) {

        db = FirebaseFirestore.getInstance()

        val doc = db.collection("Curso").document(ID)

        doc.set(model)
    }

    fun apagarCurso(ID: String){

        db = FirebaseFirestore.getInstance()

        db.collection("Disciplina").get().addOnSuccessListener {

            if(!it.isEmpty){

                for(data in it.documents){

                    val discip: DisciplinaModel? = data.toObject(DisciplinaModel::class.java)

                    if (discip != null) {
                        if(discip.cursoID == ID)  discip.apagarDiscip(discip.id)
                    }
                }
            }
        }.addOnFailureListener {        }

        db.collection("Curso").document(ID).delete()
    }
}