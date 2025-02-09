package com.example.a04_deber01.ui.recipe.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.a04_deber01.data.model.Recipe
import com.example.a04_deber01.databinding.ItemRecipeBinding
class RecipeAdapter(
    private val onItemClick: (Recipe) -> Unit,
    private val onDeleteClick: (Recipe) -> Unit,
    private val onEditClick: (Recipe) -> Unit,
    private val onViewMapClick: (Recipe) -> Unit // ✅ Nueva función para ver en el mapa
) : ListAdapter<Recipe, RecipeAdapter.ViewHolder>(DiffCallback()) {

    inner class ViewHolder(private val binding: ItemRecipeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(recipe: Recipe) {
            binding.tvRecipeName.text = recipe.name
            binding.tvPrepTime.text = "Prep Time: ${recipe.prepTime} min"
            binding.tvServings.text = "Servings: ${recipe.servings}"

            binding.root.setOnClickListener { onItemClick(recipe) }
            binding.btnDelete.setOnClickListener { onDeleteClick(recipe) }
            binding.btnEdit.setOnClickListener { onEditClick(recipe) }
            binding.btnViewMap.setOnClickListener { onViewMapClick(recipe) } // ✅ Abrir mapa
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRecipeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<Recipe>() {
        override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe) = oldItem == newItem
    }
}
