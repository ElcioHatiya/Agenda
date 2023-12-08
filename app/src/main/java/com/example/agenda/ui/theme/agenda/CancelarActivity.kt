package com.example.agenda.ui.theme.agenda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.agenda.databinding.ActivityCancelarBinding
import com.example.agendafatec.ui.theme.agenda.ReservaModel

private lateinit var o:ActivityCancelarBinding

class CancelarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        o= ActivityCancelarBinding.inflate(layoutInflater)
        setContentView(o.root)

        val obj = intent.getSerializableExtra("objeto") as ReservaModel

        var Mes = obj.mes + 1
        o.txtData.setText(obj.dia.toString()+"/"+Mes.toString())
        o.txtNomeProf.setText(obj.profNome)
        o.txtAmb.setText(obj.ambNome)

        var horario:String = when(obj.horario){

            1 -> "Horário 1 Manhã" 2 -> "Horário 2 Manhã" 3 -> "Horário 3 Manhã"
            4 -> "Horário 4 Manhã" 5 -> "Horário 5 Manhã" 6 -> "Horário 6 Manhã"
            7 -> "Horário 1 Noite" 8 -> "Horário 2 Noite" 9 -> "Horário 3 Noite"
            10 -> "Horário 4 Noite" 11 -> "Horário 5 Noite" 12 -> "Horário 6 Noite"

            else -> {""}
        }

        o.txtHora.setText(horario)

        o.btnCancelar.setOnClickListener {

            Toast.makeText(this, "Reserva cancelada!" , Toast.LENGTH_SHORT).show()

            obj.excluirReserva(obj.id)

            finish()
        }

    }
}