package com.example.a04_deber01.repository

import com.example.a04_deber01.data.model.Recipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RecipeRepository {
    private val recipes = mutableListOf<Recipe>()

    suspend fun getAllRecipes(): List<Recipe> = withContext(Dispatchers.IO) {
        recipes.toList()
    }

    suspend fun insertRecipe(recipe: Recipe) = withContext(Dispatchers.IO) {
        recipes.add(recipe.copy(id = recipes.size + 1))
    }

    suspend fun updateRecipe(updatedRecipe: Recipe) = withContext(Dispatchers.IO) {
        val index = recipes.indexOfFirst { it.id == updatedRecipe.id }
        if (index != -1) {
            recipes[index] = updatedRecipe
        }
    }

    suspend fun deleteRecipe(recipeId: Int) = withContext(Dispatchers.IO) {
        recipes.removeAll { it.id == recipeId }
    }
}
