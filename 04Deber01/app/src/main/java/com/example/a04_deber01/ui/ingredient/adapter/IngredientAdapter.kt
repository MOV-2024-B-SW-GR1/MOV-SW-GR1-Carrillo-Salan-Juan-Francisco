package com.example.recipes.ui.ingredient.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.a04_deber01.data.model.Ingredient
import com.example.a04_deber01.databinding.ItemIngredientBinding

class IngredientAdapter(
    private val onDeleteClick: (Int) -> Unit,
    private val onEditClick: (Ingredient) -> Unit
) : ListAdapter<Ingredient, IngredientAdapter.ViewHolder>(DiffCallback()) {

    inner class ViewHolder(private val binding: ItemIngredientBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(ingredient: Ingredient) {
            binding.tvIngredientName.text = ingredient.name
            binding.tvQuantity.text = "Quantity: ${ingredient.quantity} ${ingredient.unit}"

            binding.btnEdit.setOnClickListener {
                onEditClick(ingredient)
            }
            binding.btnDelete.setOnClickListener {
                onDeleteClick(ingredient.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemIngredientBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<Ingredient>() {
        override fun areItemsTheSame(oldItem: Ingredient, newItem: Ingredient) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Ingredient, newItem: Ingredient) = oldItem == newItem
    }
}
