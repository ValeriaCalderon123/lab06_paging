package com.example.lab05_room

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lab05_room.data.MAnimal

class AnimalsAdapter(private val allAnimals: List<MAnimal>) :
    RecyclerView.Adapter<AnimalsAdapter.ViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(animal: MAnimal)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.animal_row, parent, false)
        return ViewHolder(view, mListener)
    }

    override fun getItemCount() = allAnimals.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        allAnimals[position].let { holder.bind(it, position) }
        holder.itemView.setOnClickListener {
            mListener.onItemClick(allAnimals[position])
        }
    }

    class ViewHolder(view: View, listener: onItemClickListener) : RecyclerView.ViewHolder(view) {

        private val nameAnimal: TextView = itemView.findViewById(R.id.nameAnimalCard)
        private val categoryName: TextView = itemView.findViewById(R.id.categoryNameCard)
        private val statusAnimal: TextView = itemView.findViewById(R.id.statusAnimalCard)

        fun bind(it: MAnimal, position: Int) {
            nameAnimal.text = it.name_animal
            categoryName.text = it.category_name
            if(it.status_animal) {
                statusAnimal.text = "En peligro"
            } else {
                statusAnimal.text = "Fuera de Peligro"
            }
        }

    }

}
