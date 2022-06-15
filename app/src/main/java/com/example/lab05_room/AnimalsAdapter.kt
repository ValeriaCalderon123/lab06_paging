package com.example.lab05_room

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lab05_room.data.MAnimal
import com.example.lab05_room.data.entity.Animals


class AnimalsAdapter(private val allAnimals: List<Animals>) :
    RecyclerView.Adapter<AnimalsAdapter.ViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(animal: Animals)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.animal_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = allAnimals.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       // NEW
    //    holder.bind(getItem(position)!!)
       //OLD
        /*
        allAnimals[position].let { holder.bind(it, position) }
        holder.itemView.setOnClickListener {
            mListener.onItemClick(allAnimals[position])
        }*/
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val nameAnimal: TextView = itemView.findViewById(R.id.nameAnimalCard)
        private val phylumName: TextView = itemView.findViewById(R.id.phylumName)
        private val kindomName: TextView = itemView.findViewById(R.id.kindomName)

        fun bind(it: Animals) {
            nameAnimal.text = it.main_common_name
            phylumName.text = it.phylum_name
            kindomName.text = it.kingdom_name

        }

    }

}
