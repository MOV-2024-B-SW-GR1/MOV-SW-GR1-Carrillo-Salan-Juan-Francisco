package com.example.a04_deber01.repository

import com.example.a04_deber01.data.model.Ingredient

class IngredientRepository {

    private val ingredients = mutableListOf<Ingredient>()

    // Fetch ingredients by recipe ID
    fun getIngredientsByRecipe(recipeId: Int): List<Ingredient> {
        // For simplicity, filtering mock data by recipeId
        return ingredients.filter { it.id == recipeId }
    }

    // Insert a new ingredient
    fun insertIngredient(ingredient: Ingredient) {
        ingredients.add(ingredient)
    }

    // Update an ingredient
    fun updateIngredient(updatedIngredient: Ingredient) {
        ingredients.replaceAll { if (it.id == updatedIngredient.id) updatedIngredient else it }
    }

    // Delete an ingredient
    fun deleteIngredient(ingredientId: Int) {
        ingredients.removeAll { it.id == ingredientId }
    }
}
