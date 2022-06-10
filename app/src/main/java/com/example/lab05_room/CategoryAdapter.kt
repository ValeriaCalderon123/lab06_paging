package com.example.lab05_room

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lab05_room.data.entity.Category
import kotlinx.android.synthetic.main.item_category.view.*

class CategoryAdapter(val category:List<Category>): RecyclerView.Adapter<CategoryAdapter.CategoryHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(category: Category)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener=listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder{
        val layoutInflater = LayoutInflater.from(parent.context)
        return CategoryHolder(layoutInflater.inflate(R.layout.item_category,parent,false),mListener)
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
       holder.render(category[position])
        holder.itemView.setOnClickListener {
            mListener.onItemClick(category.get(position))
        }
    }

    override fun getItemCount(): Int {
        return category.size
    }
    class CategoryHolder(val view: View,listener: onItemClickListener):RecyclerView.ViewHolder(view){


        fun render(category: Category){
            view.nameCategory.text=category.name_category
            view.description_category.text=category.description
            view.number_category.text= category.quantity_animals_extinction.toString()
            view.status_category.text=category.state_category.toString()

        }
    }

}