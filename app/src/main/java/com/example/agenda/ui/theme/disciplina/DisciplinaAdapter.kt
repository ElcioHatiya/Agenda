package com.example.agendafatec.ui.theme.disciplina

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.agenda.R

class DisciplinaAdapter (private val discipList:ArrayList<DisciplinaModel>, private val listener:OnItemClickListener) : RecyclerView.Adapter<DisciplinaAdapter.DiscipViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscipViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_view_disciplina,parent,false)
        return DiscipViewHolder(itemView)
    }

    override fun getItemCount(): Int {

        return discipList.size
    }

    override fun onBindViewHolder(holder: DiscipViewHolder, position: Int) {

        val discip = discipList[position]

        holder.nome.text = discip.nome
        holder.cursoNome.text =discip.cursoNome
    }

    inner class DiscipViewHolder(itemView: View): RecyclerView.ViewHolder(itemView),
        View.OnClickListener,
        View.OnLongClickListener {

        val nome: TextView = itemView.findViewById(R.id.txtNome)
        val cursoNome: TextView = itemView.findViewById(R.id.txtCurso)

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