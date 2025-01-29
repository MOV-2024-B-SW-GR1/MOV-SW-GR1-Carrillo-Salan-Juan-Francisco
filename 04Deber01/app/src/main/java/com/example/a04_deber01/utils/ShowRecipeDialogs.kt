package com.example.a04_deber01.utils


import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.example.a04_deber01.data.model.Recipe
import com.example.a04_deber01.databinding.DialogAddRecipeBinding

fun showRecipeDialog(
    context: Context,
    onSubmit: (name: String, prepTime: Int, servings: Int) -> Unit
) {
    val binding = DialogAddRecipeBinding.inflate(LayoutInflater.from(context))
    val dialog = AlertDialog.Builder(context)
        .setTitle("Add Recipe")
        .setView(binding.root)
        .setPositiveButton("Add") { _, _ ->
            val name = binding.etRecipeName.text.toString()
            val prepTime = binding.etPrepTime.text.toString().toIntOrNull() ?: 0
            val servings = binding.etServings.text.toString().toIntOrNull() ?: 1
            onSubmit(name, prepTime, servings)
        }
        .setNegativeButton("Cancel", null)
        .create()

    dialog.show()
}

fun showUpdateRecipeDialog(
    context: Context,
    recipe: Recipe,
    onSubmit: (name: String, prepTime: Int, servings: Int) -> Unit
) {
    val binding = DialogAddRecipeBinding.inflate(LayoutInflater.from(context)).apply {
        etRecipeName.setText(recipe.name)
        etPrepTime.setText(recipe.prepTime.toString())
        etServings.setText(recipe.servings.toString())
    }

    val dialog = AlertDialog.Builder(context)
        .setTitle("Edit Recipe")
        .setView(binding.root)
        .setPositiveButton("Update") { _, _ ->
            val name = binding.etRecipeName.text.toString()
            val prepTime = binding.etPrepTime.text.toString().toIntOrNull() ?: 0
            val servings = binding.etServings.text.toString().toIntOrNull() ?: 1
            onSubmit(name, prepTime, servings)
        }
        .setNegativeButton("Cancel", null)
        .create()

    dialog.show()
}