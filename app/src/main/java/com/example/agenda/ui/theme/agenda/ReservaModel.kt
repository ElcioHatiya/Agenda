package com.example.agendafatec.ui.theme.agenda

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import java.io.Serializable

private lateinit var db: FirebaseFirestore

class ReservaModel (var id:String="",       var disponib:String="",
                    var ano:Int=0,          var mes:Int=0,           var dia:Int=0,
                    var ambID:String="",    var horario:Int=0,       var profID:String="",
                    var profNome:String="", var cursoNome:String="", var discipNome:String="", var ambNome:String=""):Serializable {

    fun salvarReserva(model: ReservaModel){

        db = FirebaseFirestore.getInstance()
        //db.firestoreSettings = FirebaseFirestoreSettings.Builder().build()

        val doc = db.collection("Reserva").document()

        model.id = doc.id
        model.disponib = "RESERVADO"

        doc.set(model)
    }

    fun excluirReserva(ID: String){

        db = FirebaseFirestore.getInstance()

        db.firestoreSettings = FirebaseFirestoreSettings.Builder().build()

        db.collection("Reserva").document(ID).delete()
    }
}