package com.example.tp5.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tp5.Objects.Deportes
import com.example.tp5.R


class deportesListAdapter (private var deportesList : MutableList<Deportes>,var context:Context,val onItemClick : (Int) -> Unit) : RecyclerView.Adapter<deportesListAdapter.deportesHolder>() {

    companion object {

        private val TAG = "DeportesListAdapter"
    }

    override fun onBindViewHolder(holder: deportesHolder, position: Int) {

        holder.setName(deportesList[position].horario)
        holder.setType(deportesList[position].costo.toString())

        Glide
            .with(context)
            .load(deportesList[position].imagen_partido)
            .centerInside()
            .into(holder.getImageView());

        holder.getCardLayout().setOnClickListener {
            onItemClick(position)
        }

    }

    override fun getItemCount(): Int {
        return deportesList.size
    }

    fun setData(newData: ArrayList<Deportes>) {
        this.deportesList = newData
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): deportesHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_deporte,parent,false)
        return (deportesHolder(view))
    }


    class deportesHolder (v : View) : RecyclerView.ViewHolder(v){

        private var v : View
        init{
            this.v = v
        }


        fun setName(name: String){
            val tvName : TextView = v.findViewById(R.id.txt_nombre)
            tvName.text = name

        }

        fun setType(tipo: String){
            val tvTipo : TextView = v.findViewById(R.id.txt_tipo)
            tvTipo.text = tipo

        }

        fun getImageView () : ImageView {
            return v.findViewById(R.id.imageView2)


        }

            fun getCardLayout ():CardView{
                return v.findViewById(R.id.card_package_item)
            }
    }




}