package com.example.a04_deber01.utils

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.example.a04_deber01.data.model.Ingredient
import com.example.a04_deber01.databinding.DialogAddIngredientBinding

fun showIngredientDialog(
    context: Context,
    onSubmit: (name: String, quantity: Double, unit: String) -> Unit
) {
    val binding = DialogAddIngredientBinding.inflate(LayoutInflater.from(context))
    val dialog = AlertDialog.Builder(context)
        .setTitle("Add Ingredient")
        .setView(binding.root)
        .setPositiveButton("Add") { _, _ ->
            val name = binding.etName.text.toString()
            val quantity = binding.etQuantity.text.toString().toDoubleOrNull() ?: 0.0
            val unit = binding.etUnit.text.toString()
            onSubmit(name, quantity, unit)
        }
        .setNegativeButton("Cancel", null)
        .create()

    dialog.show()
}

fun showUpdateIngredientDialog(
    context: Context,
    ingredient: Ingredient,
    onSubmit: (name: String, quantity: Double, unit: String) -> Unit
) {
    val binding = DialogAddIngredientBinding.inflate(LayoutInflater.from(context)).apply {
        etName.setText(ingredient.name)
        etQuantity.setText(ingredient.quantity.toString())
        etUnit.setText(ingredient.unit)
    }

    val dialog = AlertDialog.Builder(context)
        .setTitle("Edit Ingredient")
        .setView(binding.root)
        .setPositiveButton("Update") { _, _ ->
            val name = binding.etName.text.toString()
            val quantity = binding.etQuantity.text.toString().toDoubleOrNull() ?: 0.0
            val unit = binding.etUnit.text.toString()
            onSubmit(name, quantity, unit)
        }
        .setNegativeButton("Cancel", null)
        .create()

    dialog.show()
}