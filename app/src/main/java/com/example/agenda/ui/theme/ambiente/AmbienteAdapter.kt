package com.example.agendafatec.ui.theme.ambiente

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.agenda.R

class AmbienteAdapter(private val ambList:ArrayList<AmbienteModel>, private val listener:OnItemClickListener) : RecyclerView.Adapter<AmbienteAdapter.AmbViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AmbViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_view_ambiente,parent,false)
        return AmbViewHolder(itemView)

    }

    override fun getItemCount(): Int {

        return ambList.size

    }

    override fun onBindViewHolder(holder: AmbViewHolder, position: Int) {

        val amb = ambList[position]

        holder.nome.text = amb.nome
        holder.acesso.text = amb.acesso
        holder.nivel.text = amb.nivel

    }

    inner class AmbViewHolder(itemView: View):RecyclerView.ViewHolder(itemView),
        View.OnClickListener,
        View.OnLongClickListener {

        val nome:TextView = itemView.findViewById(R.id.txtNome)
        val acesso:TextView = itemView.findViewById(R.id.txtAcesso)
        val nivel:TextView = itemView.findViewById(R.id.txtNivel)

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