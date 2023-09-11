package com.example.seminariodelenguajes

import android.content.Context
import android.view.LayoutInflater
import android.widget.TextView
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class SuperheroeAdapter(var superheroes: MutableList<Superheroe>, var context: Context):
RecyclerView.Adapter<SuperheroeAdapter.SuperheroeViewHolder>(){

    override fun onBindViewHolder(holder: SuperheroeViewHolder, position: Int) {
        val item = superheroes.get(position)
        holder.txtNombre.text = item.nombre
        //holder.txtFechaNac.text = item.fechaNacimiento

        holder.itemView.setOnClickListener( View.OnClickListener{
            Toast.makeText(context, item.nombre, Toast.LENGTH_SHORT).show()
        })
    }
    override fun getItemCount() = superheroes.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperheroeViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_superheroe, parent, false)

        return SuperheroeViewHolder((view))
    }

    class SuperheroeViewHolder(view:View): RecyclerView.ViewHolder(view){
        var txtNombre: TextView
       // var txtFechaNac: TextView

        init{
            txtNombre = view.findViewById(R.id.mostrarNombre)
           // txtFechaNac = view.findViewById(R.id.mostrarFechaNac)
        }

    }


}