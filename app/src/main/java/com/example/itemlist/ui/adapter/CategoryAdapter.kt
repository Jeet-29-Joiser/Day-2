package com.example.itemlist.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.itemlist.data.Category
import com.example.itemlist.databinding.ItemCategoryBinding

class CategoryAdapter(
    private val categoryList: List<Category>,
    private val onClick:(Category)->Unit
): RecyclerView.Adapter<CategoryAdapter.ViewHolder>(){

    class ViewHolder(val binding: ItemCategoryBinding)
        :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = ItemCategoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val category = categoryList[position]

        holder.binding.tvCategoryName.text = category.name

        // THIS LINE shows the correct category image
        holder.binding.ivCategory.setImageResource(category.image)

        holder.itemView.setOnClickListener {
            onClick(category)
        }
    }

    override fun getItemCount() = categoryList.size
}