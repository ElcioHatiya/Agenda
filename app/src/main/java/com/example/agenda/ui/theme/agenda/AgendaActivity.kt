package com.example.agenda.ui.theme.agenda

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import com.example.agenda.MainActivity
import com.example.agenda.R
import com.example.agenda.databinding.ActivityAgendaBinding
import com.example.agenda.ui.theme.usuario.UsuarioModel
import com.example.agendafatec.ui.theme.agenda.ReservaModel
import com.example.agendafatec.ui.theme.ambiente.AmbienteModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.Calendar

private lateinit var o:ActivityAgendaBinding
private var db = Firebase.firestore
private lateinit var listaAmb:ArrayList<AmbienteModel>

class AgendaActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    var AUTHNIVEL:String = ""
    var AUTHID:String = ""
    var AUTHNOME:String = ""
    var AMBID:String = ""
    var AMBNOME:String = ""
    var ANO:Int = 0
    var MES:Int = 0
    var DIA:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        o=ActivityAgendaBinding.inflate(layoutInflater)
        setContentView(o.root)

        listaAmb = arrayListOf()

        val obj = intent.getSerializableExtra("objeto") as UsuarioModel

        AUTHNIVEL = obj.nivel
        AUTHID = obj.id
        AUTHNOME = obj.nome

        carregarSpinner(AUTHNIVEL)

        exibeCalendario()

        o.btnData.setOnClickListener { exibeCalendario() }

        o.spnT.onItemSelectedListener = object : AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {

                var amb = listaAmb[position]
                AMBNOME = amb.nome
                AMBID = amb.id

                atualizarLista(ANO, MES, DIA, AMBID)
            }
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {}
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        o.btnLogout.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        o.item1.setOnClickListener {

            if (AUTHNIVEL == "ALUNO") {

            } else {

                var r1 = gerarReserva(1)

                if (o.txtNome1.text == "LIVRE") {

                    if (AUTHNIVEL == "ETEC" || AUTHNIVEL == "FATEC") {

                        val intent = Intent(this, ReservarActivity::class.java)
                        intent.putExtra("nivel", AUTHNIVEL)
                        intent.putExtra("objeto", r1)
                        startActivity(intent)
                    }

                }else if(o.txtProfID1.text == AUTHID){

                    r1.id = o.txtID1.text.toString()

                    val intent = Intent(this, CancelarActivity::class.java)
                    intent.putExtra("objeto", r1)
                    startActivity(intent)

                } else {

                    if (AUTHNIVEL == "ADMIN") {

                        r1.id = o.txtID1.text.toString()

                        val intent = Intent(this, CancelarActivity::class.java)
                        intent.putExtra("objeto", r1)
                        startActivity(intent)

                    }else{
                        Toast.makeText(this, "Reservado", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        o.item2.setOnClickListener {

            if (AUTHNIVEL == "ALUNO") {

            } else {

                var r2 = gerarReserva(2)

                if (o.txtNome2.text == "LIVRE") {

                    if (AUTHNIVEL == "ETEC" || AUTHNIVEL == "FATEC") {

                        val intent = Intent(this, ReservarActivity::class.java)
                        intent.putExtra("nivel", AUTHNIVEL)
                        intent.putExtra("objeto", r2)
                        startActivity(intent)

                    }

                }else if(o.txtProfID2.text == AUTHID){

                    r2.id = o.txtID2.text.toString()

                    val intent = Intent(this, CancelarActivity::class.java)
                    intent.putExtra("objeto", r2)
                    startActivity(intent)

                } else {

                    if (AUTHNIVEL == "ADMIN") {

                        r2.id = o.txtID2.text.toString()

                        val intent = Intent(this, CancelarActivity::class.java)
                        intent.putExtra("objeto", r2)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this, "Reservado", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        o.item3.setOnClickListener {

            if (AUTHNIVEL == "ALUNO") {

            } else {

                var r3 = gerarReserva(3)

                if (o.txtNome3.text == "LIVRE") {

                    if (AUTHNIVEL == "ETEC" || AUTHNIVEL == "FATEC") {

                        val intent = Intent(this, ReservarActivity::class.java)
                        intent.putExtra("nivel", AUTHNIVEL)
                        intent.putExtra("objeto", r3)
                        startActivity(intent)

                    }

                }else if(o.txtProfID3.text == AUTHID){

                    r3.id = o.txtID3.text.toString()

                    val intent = Intent(this, CancelarActivity::class.java)
                    intent.putExtra("objeto", r3)
                    startActivity(intent)

                } else {

                    if (AUTHNIVEL == "ADMIN") {

                        r3.id = o.txtID3.text.toString()

                        val intent = Intent(this, CancelarActivity::class.java)
                        intent.putExtra("objeto", r3)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this, "Reservado", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        o.item4.setOnClickListener {

            if (AUTHNIVEL == "ALUNO") {

            } else {

                var r4 = gerarReserva(4)

                if (o.txtNome4.text == "LIVRE") {

                    if (AUTHNIVEL == "ETEC" || AUTHNIVEL == "FATEC") {

                        val intent = Intent(this, ReservarActivity::class.java)
                        intent.putExtra("nivel", AUTHNIVEL)
                        intent.putExtra("objeto", r4)
                        startActivity(intent)

                    }

                }else if(o.txtProfID4.text == AUTHID){

                    r4.id = o.txtID4.text.toString()

                    val intent = Intent(this, CancelarActivity::class.java)
                    intent.putExtra("objeto", r4)
                    startActivity(intent)

                } else {

                    if (AUTHNIVEL == "ADMIN") {

                        r4.id = o.txtID4.text.toString()

                        val intent = Intent(this, CancelarActivity::class.java)
                        intent.putExtra("objeto", r4)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this, "Reservado", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        o.item5.setOnClickListener {

            if (AUTHNIVEL == "ALUNO") {

            } else {

                var r5 = gerarReserva(5)

                if (o.txtNome5.text == "LIVRE") {

                    if (AUTHNIVEL == "ETEC" || AUTHNIVEL == "FATEC") {

                        val intent = Intent(this, ReservarActivity::class.java)
                        intent.putExtra("nivel", AUTHNIVEL)
                        intent.putExtra("objeto", r5)
                        startActivity(intent)

                    }

                }else if(o.txtProfID5.text == AUTHID){

                    r5.id = o.txtID5.text.toString()

                    val intent = Intent(this, CancelarActivity::class.java)
                    intent.putExtra("objeto", r5)
                    startActivity(intent)

                } else {

                    if (AUTHNIVEL == "ADMIN") {

                        r5.id = o.txtID5.text.toString()

                        val intent = Intent(this, CancelarActivity::class.java)
                        intent.putExtra("objeto", r5)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this, "Reservado", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        o.item6.setOnClickListener {

            if (AUTHNIVEL == "ALUNO") {

            } else {

                var r6 = gerarReserva(6)

                if (o.txtNome6.text == "LIVRE") {

                    if (AUTHNIVEL == "ETEC" || AUTHNIVEL == "FATEC") {

                        val intent = Intent(this, ReservarActivity::class.java)
                        intent.putExtra("nivel", AUTHNIVEL)
                        intent.putExtra("objeto", r6)
                        startActivity(intent)

                    }

                }else if(o.txtProfID6.text == AUTHID){

                    r6.id = o.txtID6.text.toString()

                    val intent = Intent(this, CancelarActivity::class.java)
                    intent.putExtra("objeto", r6)
                    startActivity(intent)

                } else {

                    if (AUTHNIVEL == "ADMIN") {

                        r6.id = o.txtID6.text.toString()

                        val intent = Intent(this, CancelarActivity::class.java)
                        intent.putExtra("objeto", r6)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this, "Reservado", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        o.item7.setOnClickListener {

            if (AUTHNIVEL == "ALUNO") {

            } else {

                var r7 = gerarReserva(7)

                if (o.txtNome7.text == "LIVRE") {

                    if (AUTHNIVEL == "ETEC" || AUTHNIVEL == "FATEC") {

                        val intent = Intent(this, ReservarActivity::class.java)
                        intent.putExtra("nivel", AUTHNIVEL)
                        intent.putExtra("objeto", r7)
                        startActivity(intent)

                    }

                }else if(o.txtProfID7.text == AUTHID){

                    r7.id = o.txtID7.text.toString()

                    val intent = Intent(this, CancelarActivity::class.java)
                    intent.putExtra("objeto", r7)
                    startActivity(intent)

                } else {

                    if (AUTHNIVEL == "ADMIN") {

                        r7.id = o.txtID7.text.toString()

                        val intent = Intent(this, CancelarActivity::class.java)
                        intent.putExtra("objeto", r7)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this, "Reservado", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        o.item8.setOnClickListener {

            if (AUTHNIVEL == "ALUNO") {

            } else {

                var r8 = gerarReserva(8)

                if (o.txtNome8.text == "LIVRE") {

                    if (AUTHNIVEL == "ETEC" || AUTHNIVEL == "FATEC") {

                        val intent = Intent(this, ReservarActivity::class.java)
                        intent.putExtra("nivel", AUTHNIVEL)
                        intent.putExtra("objeto", r8)
                        startActivity(intent)

                    }

                }else if(o.txtProfID8.text == AUTHID){

                    r8.id = o.txtID8.text.toString()

                    val intent = Intent(this, CancelarActivity::class.java)
                    intent.putExtra("objeto", r8)
                    startActivity(intent)

                } else {

                    if (AUTHNIVEL == "ADMIN") {

                        r8.id = o.txtID8.text.toString()

                        val intent = Intent(this, CancelarActivity::class.java)
                        intent.putExtra("objeto", r8)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this, "Reservado", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        o.item9.setOnClickListener {

            if (AUTHNIVEL == "ALUNO") {

            } else {

                var r9 = gerarReserva(9)

                if (o.txtNome9.text == "LIVRE") {

                    if (AUTHNIVEL == "ETEC" || AUTHNIVEL == "FATEC") {

                        val intent = Intent(this, ReservarActivity::class.java)
                        intent.putExtra("nivel", AUTHNIVEL)
                        intent.putExtra("objeto", r9)
                        startActivity(intent)

                    }

                }else if(o.txtProfID9.text == AUTHID){

                    r9.id = o.txtID9.text.toString()

                    val intent = Intent(this, CancelarActivity::class.java)
                    intent.putExtra("objeto", r9)
                    startActivity(intent)

                } else {

                    if (AUTHNIVEL == "ADMIN") {

                        r9.id = o.txtID9.text.toString()

                        val intent = Intent(this, CancelarActivity::class.java)
                        intent.putExtra("objeto", r9)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this, "Reservado", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        o.item10.setOnClickListener {

            if (AUTHNIVEL == "ALUNO") {

            } else {

                var r10 = gerarReserva(10)

                if (o.txtNome10.text == "LIVRE") {

                    if (AUTHNIVEL == "ETEC" || AUTHNIVEL == "FATEC") {

                        val intent = Intent(this, ReservarActivity::class.java)
                        intent.putExtra("nivel", AUTHNIVEL)
                        intent.putExtra("objeto", r10)
                        startActivity(intent)

                    }

                }else if(o.txtProfID10.text == AUTHID){

                    r10.id = o.txtID10.text.toString()

                    val intent = Intent(this, CancelarActivity::class.java)
                    intent.putExtra("objeto", r10)
                    startActivity(intent)

                } else {

                    if (AUTHNIVEL == "ADMIN") {

                        r10.id = o.txtID10.text.toString()

                        val intent = Intent(this, CancelarActivity::class.java)
                        intent.putExtra("objeto", r10)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this, "Reservado", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        o.item11.setOnClickListener {

            if (AUTHNIVEL == "ALUNO") {

            } else {

                var r11 = gerarReserva(11)

                if (o.txtNome11.text == "LIVRE") {

                    if (AUTHNIVEL == "ETEC" || AUTHNIVEL == "FATEC") {

                        val intent = Intent(this, ReservarActivity::class.java)
                        intent.putExtra("nivel", AUTHNIVEL)
                        intent.putExtra("objeto", r11)
                        startActivity(intent)

                    }

                }else if(o.txtProfID11.text == AUTHID){

                    r11.id = o.txtID11.text.toString()

                    val intent = Intent(this, CancelarActivity::class.java)
                    intent.putExtra("objeto", r11)
                    startActivity(intent)

                } else {

                    if (AUTHNIVEL == "ADMIN") {

                        r11.id = o.txtID11.text.toString()

                        val intent = Intent(this, CancelarActivity::class.java)
                        intent.putExtra("objeto", r11)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this, "Reservado", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        o.item12.setOnClickListener {

            if (AUTHNIVEL == "ALUNO") {

            } else {

                var r12 = gerarReserva(12)

                if (o.txtNome12.text == "LIVRE") {

                    if (AUTHNIVEL == "ETEC" || AUTHNIVEL == "FATEC") {

                        val intent = Intent(this, ReservarActivity::class.java)
                        intent.putExtra("nivel", AUTHNIVEL)
                        intent.putExtra("objeto", r12)
                        startActivity(intent)

                    }

                }else if(o.txtProfID12.text == AUTHID){

                    r12.id = o.txtID12.text.toString()

                    val intent = Intent(this, CancelarActivity::class.java)
                    intent.putExtra("objeto", r12)
                    startActivity(intent)

                }else{

                    if (AUTHNIVEL == "ADMIN") {

                        r12.id = o.txtID12.text.toString()

                        val intent = Intent(this, CancelarActivity::class.java)
                        intent.putExtra("objeto", r12)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this, "Reservado", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun carregarSpinner(niv:String) {

        db.collection("Ambiente").get().addOnSuccessListener {

            if (!it.isEmpty) {

                for (data in it.documents) {

                    val amb: AmbienteModel? = data.toObject(AmbienteModel::class.java)
                    if (amb != null)

                        if ((((AUTHNIVEL == "ADMIN" || AUTHNIVEL == "ALUNO") ||
                                    (amb.nivel == niv || amb.nivel == "PUBLICO")) && amb.acesso == "ATIVO")) listaAmb.add(amb)
                }

                o.spnT.adapter = ArrayAdapter(this,R.layout.spinner_amb_layout,R.id.txtAmbSpinner,listaAmb)
            }
        }.addOnFailureListener {
            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    fun exibeCalendario(){

        val dialog = DatePickerDialog(this,this, Calendar.getInstance().get(Calendar.YEAR),

            Calendar.getInstance().get(Calendar.MONTH),
            Calendar.getInstance().get(Calendar.DAY_OF_MONTH))

        val now = System.currentTimeMillis()

        dialog.setCancelable(false)
        dialog.datePicker.minDate = now - 1000 * 60 * 60 * 24 * 15
        dialog.datePicker.maxDate = now + 1000 * 60 * 60 * 24 * 15

        dialog.show()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {

        ANO = year
        MES = month
        DIA = dayOfMonth

        atualizarLista(year,month,dayOfMonth,AMBID)

        o.btnData.setText(dayOfMonth.toString() + "/" + (month+1).toString())
    }

    fun gerarReserva(hor:Int): ReservaModel {

        var model = ReservaModel()

        with(model) {
            ambID = AMBID
            ambNome = AMBNOME
            ano = ANO
            mes = MES
            dia = DIA
            horario = hor
            profID = AUTHID
            profNome = AUTHNOME
        }
        return model
    }

    override fun onResume() {
        super.onResume()

        atualizarLista(ANO,MES,DIA, AMBID)
    }

    private fun atualizarLista(Ano:Int, Mes:Int, Dia:Int, Amb:String){

        db.collection("Reserva")
            .whereEqualTo("ano", Ano)
            .whereEqualTo("mes", Mes)
            .whereEqualTo("dia", Dia)
            .whereEqualTo("horario", 1)
            .whereEqualTo("ambID", Amb)
            .whereEqualTo("disponib", "RESERVADO")
            .get().addOnSuccessListener { view ->

                if (!view.isEmpty) {

                    for (data in view.documents) {

                        var r1 = data.toObject(ReservaModel::class.java)!!
                        if (r1 != null) {

                            o.txtNome1.setText(r1.profNome)
                            o.txtCurso1.setText(r1.cursoNome)
                            o.txtDisc1.setText(r1.discipNome)
                            o.txtID1.setText(r1.id)
                            o.txtProfID1.setText(r1.profID)
                            o.item1.setBackgroundColor(resources.getColor(R.color.agendaVemelho))
                        }
                    }

                }else{

                    o.item1.setBackgroundColor(resources.getColor(R.color.agendaVerde))
                    o.txtNome1.setText("LIVRE")
                    o.txtCurso1.setText("")
                    o.txtDisc1.setText("")
                }
            }

        db.collection("Reserva")
            .whereEqualTo("ano", Ano)
            .whereEqualTo("mes", Mes)
            .whereEqualTo("dia", Dia)
            .whereEqualTo("horario", 2)
            .whereEqualTo("ambID", Amb)
            .whereEqualTo("disponib", "RESERVADO")
            .get().addOnSuccessListener { view ->

                if (!view.isEmpty) {

                    for (data in view.documents) {

                        var r2 = data.toObject(ReservaModel::class.java)
                        if (r2 != null) {
                            o.txtNome2.setText(r2.profNome)
                            o.txtCurso2.setText(r2.cursoNome)
                            o.txtDisc2.setText(r2.discipNome)
                            o.txtID2.setText(r2.id)
                            o.txtProfID2.setText(r2.profID)
                            o.item2.setBackgroundColor(resources.getColor(R.color.agendaVemelho))
                        }
                    }

                }else{

                    o.item2.setBackgroundColor(resources.getColor(R.color.agendaVerde))
                    o.txtNome2.setText("LIVRE")
                    o.txtCurso2.setText("")
                    o.txtDisc2.setText("")
                }
            }

        db.collection("Reserva")
            .whereEqualTo("ano", Ano)
            .whereEqualTo("mes", Mes)
            .whereEqualTo("dia", Dia)
            .whereEqualTo("horario", 3)
            .whereEqualTo("ambID", Amb)
            .whereEqualTo("disponib", "RESERVADO")
            .get().addOnSuccessListener { view ->

                if (!view.isEmpty) {

                    for (data in view.documents) {

                        var r3 = data.toObject(ReservaModel::class.java)
                        if (r3 != null) {
                            o.txtNome3.setText(r3.profNome)
                            o.txtCurso3.setText(r3.cursoNome)
                            o.txtDisc3.setText(r3.discipNome)
                            o.txtID3.setText(r3.id)
                            o.txtProfID3.setText(r3.profID)
                            o.item3.setBackgroundColor(resources.getColor(R.color.agendaVemelho))
                        }
                    }

                }else{

                    o.item3.setBackgroundColor(resources.getColor(R.color.agendaVerde))
                    o.txtNome3.setText("LIVRE")
                    o.txtCurso3.setText("")
                    o.txtDisc3.setText("")
                }
            }

        db.collection("Reserva")
            .whereEqualTo("ano", Ano)
            .whereEqualTo("mes", Mes)
            .whereEqualTo("dia", Dia)
            .whereEqualTo("horario", 4)
            .whereEqualTo("ambID", Amb)
            .whereEqualTo("disponib", "RESERVADO")
            .get().addOnSuccessListener { view ->

                if (!view.isEmpty) {

                    for (data in view.documents) {

                        var r4 = data.toObject(ReservaModel::class.java)
                        if (r4 != null) {
                            o.txtNome4.setText(r4.profNome)
                            o.txtCurso4.setText(r4.cursoNome)
                            o.txtDisc4.setText(r4.discipNome)
                            o.txtID4.setText(r4.id)
                            o.txtProfID4.setText(r4.profID)
                            o.item4.setBackgroundColor(resources.getColor(R.color.agendaVemelho))
                        }
                    }

                }else{

                    o.item4.setBackgroundColor(resources.getColor(R.color.agendaVerde))
                    o.txtNome4.setText("LIVRE")
                    o.txtCurso4.setText("")
                    o.txtDisc4.setText("")
                }
            }

        db.collection("Reserva")
            .whereEqualTo("ano", Ano)
            .whereEqualTo("mes", Mes)
            .whereEqualTo("dia", Dia)
            .whereEqualTo("horario", 5)
            .whereEqualTo("ambID", Amb)
            .whereEqualTo("disponib", "RESERVADO")
            .get().addOnSuccessListener { view ->

                if (!view.isEmpty) {

                    for (data in view.documents) {

                        var r5 = data.toObject(ReservaModel::class.java)
                        if (r5 != null) {
                            o.txtNome5.setText(r5.profNome)
                            o.txtCurso5.setText(r5.cursoNome)
                            o.txtDisc5.setText(r5.discipNome)
                            o.txtID5.setText(r5.id)
                            o.txtProfID5.setText(r5.profID)
                            o.item5.setBackgroundColor(resources.getColor(R.color.agendaVemelho))
                        }
                    }

                }else{

                    o.item5.setBackgroundColor(resources.getColor(R.color.agendaVerde))
                    o.txtNome5.setText("LIVRE")
                    o.txtCurso5.setText("")
                    o.txtDisc5.setText("")
                }
            }

        db.collection("Reserva")
            .whereEqualTo("ano", Ano)
            .whereEqualTo("mes", Mes)
            .whereEqualTo("dia", Dia)
            .whereEqualTo("horario", 6)
            .whereEqualTo("ambID", Amb)
            .whereEqualTo("disponib", "RESERVADO")
            .get().addOnSuccessListener { view ->

                if (!view.isEmpty) {

                    for (data in view.documents) {

                        var r6 = data.toObject(ReservaModel::class.java)
                        if (r6 != null) {
                            o.txtNome6.setText(r6.profNome)
                            o.txtCurso6.setText(r6.cursoNome)
                            o.txtDisc6.setText(r6.discipNome)
                            o.txtID6.setText(r6.id)
                            o.txtProfID6.setText(r6.profID)
                            o.item6.setBackgroundColor(resources.getColor(R.color.agendaVemelho))
                        }
                    }
                }

                else{

                    o.item6.setBackgroundColor(resources.getColor(R.color.agendaVerde))
                    o.txtNome6.setText("LIVRE")
                    o.txtCurso6.setText("")
                    o.txtDisc6.setText("")
                }
            }

        db.collection("Reserva")
            .whereEqualTo("ano", Ano)
            .whereEqualTo("mes", Mes)
            .whereEqualTo("dia", Dia)
            .whereEqualTo("horario", 7)
            .whereEqualTo("ambID", Amb)
            .whereEqualTo("disponib", "RESERVADO")
            .get().addOnSuccessListener { view ->

                if (!view.isEmpty) {

                    for (data in view.documents) {

                        var r7 = data.toObject(ReservaModel::class.java)
                        if (r7 != null) {
                            o.txtNome7.setText(r7.profNome)
                            o.txtCurso7.setText(r7.cursoNome)
                            o.txtDisc7.setText(r7.discipNome)
                            o.txtID7.setText(r7.id)
                            o.txtProfID7.setText(r7.profID)
                            o.item7.setBackgroundColor(resources.getColor(R.color.agendaVemelho))
                        }
                    }

                }else{

                    o.item7.setBackgroundColor(resources.getColor(R.color.agendaVerde))
                    o.txtNome7.setText("LIVRE")
                    o.txtCurso7.setText("")
                    o.txtDisc7.setText("")
                }
            }

        db.collection("Reserva")
            .whereEqualTo("ano", Ano)
            .whereEqualTo("mes", Mes)
            .whereEqualTo("dia", Dia)
            .whereEqualTo("horario", 8)
            .whereEqualTo("ambID", Amb)
            .whereEqualTo("disponib", "RESERVADO")
            .get().addOnSuccessListener { view ->

                if (!view.isEmpty) {

                    for (data in view.documents) {

                        var r8 = data.toObject(ReservaModel::class.java)
                        if (r8 != null) {
                            o.txtNome8.setText(r8.profNome)
                            o.txtCurso8.setText(r8.cursoNome)
                            o.txtDisc8.setText(r8.discipNome)
                            o.txtID8.setText(r8.id)
                            o.txtProfID8.setText(r8.profID)
                            o.item8.setBackgroundColor(resources.getColor(R.color.agendaVemelho))
                        }
                    }
                }else{
                    o.item8.setBackgroundColor(resources.getColor(R.color.agendaVerde))
                    o.txtNome8.setText("LIVRE")
                    o.txtCurso8.setText("")
                    o.txtDisc8.setText("")
                }
            }

        db.collection("Reserva")
            .whereEqualTo("ano", Ano)
            .whereEqualTo("mes", Mes)
            .whereEqualTo("dia", Dia)
            .whereEqualTo("horario", 9)
            .whereEqualTo("ambID", Amb)
            .whereEqualTo("disponib", "RESERVADO")
            .get().addOnSuccessListener { view ->

                if (!view.isEmpty) {

                    for (data in view.documents) {

                        var r9 = data.toObject(ReservaModel::class.java)
                        if (r9 != null) {
                            o.txtNome9.setText(r9.profNome)
                            o.txtCurso9.setText(r9.cursoNome)
                            o.txtDisc9.setText(r9.discipNome)
                            o.txtID9.setText(r9.id)
                            o.txtProfID9.setText(r9.profID)
                            o.item9.setBackgroundColor(resources.getColor(R.color.agendaVemelho))
                        }
                    }
                }else{
                    o.item9.setBackgroundColor(resources.getColor(R.color.agendaVerde))
                    o.txtNome9.setText("LIVRE")
                    o.txtCurso9.setText("")
                    o.txtDisc9.setText("")
                }
            }

        db.collection("Reserva")
            .whereEqualTo("ano", Ano)
            .whereEqualTo("mes", Mes)
            .whereEqualTo("dia", Dia)
            .whereEqualTo("horario", 10)
            .whereEqualTo("ambID", Amb)
            .whereEqualTo("disponib", "RESERVADO")
            .get().addOnSuccessListener { view ->

                if (!view.isEmpty) {

                    for (data in view.documents) {

                        var r10 = data.toObject(ReservaModel::class.java)
                        if (r10 != null) {
                            o.txtNome10.setText(r10.profNome)
                            o.txtCurso10.setText(r10.cursoNome)
                            o.txtDisc10.setText(r10.discipNome)
                            o.txtID10.setText(r10.id)
                            o.txtProfID10.setText(r10.profID)
                            o.item10.setBackgroundColor(resources.getColor(R.color.agendaVemelho))
                        }
                    }
                }else{
                    o.item10.setBackgroundColor(resources.getColor(R.color.agendaVerde))
                    o.txtNome10.setText("LIVRE")
                    o.txtCurso10.setText("")
                    o.txtDisc10.setText("")
                }
            }

        db.collection("Reserva")
            .whereEqualTo("ano", Ano)
            .whereEqualTo("mes", Mes)
            .whereEqualTo("dia", Dia)
            .whereEqualTo("horario", 11)
            .whereEqualTo("ambID", Amb)
            .whereEqualTo("disponib", "RESERVADO")
            .get().addOnSuccessListener { view ->

                if (!view.isEmpty) {

                    for (data in view.documents) {

                        var r11 = data.toObject(ReservaModel::class.java)
                        if (r11 != null) {
                            o.txtNome11.setText(r11.profNome)
                            o.txtCurso11.setText(r11.cursoNome)
                            o.txtDisc11.setText(r11.discipNome)
                            o.txtID11.setText(r11.id)
                            o.txtProfID11.setText(r11.profID)
                            o.item11.setBackgroundColor(resources.getColor(R.color.agendaVemelho))
                        }
                    }
                }else{
                    o.item11.setBackgroundColor(resources.getColor(R.color.agendaVerde))
                    o.txtNome11.setText("LIVRE")
                    o.txtCurso11.setText("")
                    o.txtDisc11.setText("")
                }
            }

        db.collection("Reserva")
            .whereEqualTo("ano", Ano)
            .whereEqualTo("mes", Mes)
            .whereEqualTo("dia", Dia)
            .whereEqualTo("horario", 12)
            .whereEqualTo("ambID", Amb)
            .whereEqualTo("disponib", "RESERVADO")
            .get().addOnSuccessListener { view ->

                if (!view.isEmpty) {

                    for (data in view.documents) {

                        var r12 = data.toObject(ReservaModel::class.java)
                        if (r12 != null) {
                            o.txtNome12.setText(r12.profNome)
                            o.txtCurso12.setText(r12.cursoNome)
                            o.txtDisc12.setText(r12.discipNome)
                            o.txtID12.setText(r12.id)
                            o.txtProfID12.setText(r12.profID)
                            o.item12.setBackgroundColor(resources.getColor(R.color.agendaVemelho))
                        }
                    }
                }else{
                    o.item12.setBackgroundColor(resources.getColor(R.color.agendaVerde))
                    o.txtNome12.setText("LIVRE")
                    o.txtCurso12.setText("")
                    o.txtDisc12.setText("")
                }
            }
    }
}