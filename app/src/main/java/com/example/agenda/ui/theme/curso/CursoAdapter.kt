package com.example.agendafatec.ui.theme.curso

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.agenda.R


class CursoAdapter(private val cursoList:ArrayList<CursoModel>, private val listener:OnItemClickListener) : RecyclerView.Adapter<CursoAdapter.CursoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CursoViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_view_curso,parent,false)
        return CursoViewHolder(itemView)
    }

    override fun getItemCount(): Int {

        return cursoList.size
    }

    override fun onBindViewHolder(holder: CursoViewHolder, position: Int) {

        val amb = cursoList[position]

        holder.nome.text = amb.nome
        holder.nivel.text = amb.nivel
    }

    inner class CursoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView),
        View.OnClickListener,
        View.OnLongClickListener {

        val nome: TextView = itemView.findViewById(R.id.txtNome)
        val nivel: TextView = itemView.findViewById(R.id.txtNivel)

        init{itemView.setOnClickListener(this)}
        init{itemView.setOnLongClickListener(this)}

        override fun onClick(p0: View?) {
            val position: Int = adapterPosition
            if(position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }

        override fun onLongClick(p0: View?):Boolean {
            val position: Int = adapterPosition
            if(position != RecyclerView.NO_POSITION) {
                listener.onLongClick(position)
            }
            return true
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
        fun onLongClick(position: Int)
    }
}