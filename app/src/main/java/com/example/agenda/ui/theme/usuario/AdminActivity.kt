package com.example.agenda.ui.theme.usuario

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.agenda.MainActivity
import com.example.agenda.R
import com.example.agenda.databinding.ActivityAdminBinding
import com.example.agenda.ui.theme.agenda.AgendaActivity
import com.example.agenda.ui.theme.ambiente.AmbienteActivity
import com.example.agenda.ui.theme.curso.CursoActivity
import com.example.agendafatec.ui.theme.agenda.ReservaModel

private lateinit var o:ActivityAdminBinding

class AdminActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        o=ActivityAdminBinding.inflate(layoutInflater)
        setContentView(o.root)

        o.btnProf.setOnClickListener {

            val intent = Intent(this, UsuarioActivity::class.java)
            startActivity(intent)
        }

        o.btnAmb.setOnClickListener {

            val intent = Intent(this, AmbienteActivity::class.java)
            startActivity(intent)
        }

        o.btnAgenda.setOnClickListener {

            val intent = Intent(this, AgendaActivity::class.java)
            var obj = UsuarioModel()
            obj.nivel = "ADMIN"
            intent.putExtra("objeto",obj)
            startActivity(intent)
        }

        o.btnCursos.setOnClickListener {

            val intent = Intent(this, CursoActivity::class.java)
            startActivity(intent)
        }

        o.btnLogout.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}