package com.example.a04_deber01.repository

import com.example.a04_deber01.data.dao.RecipeDao
import com.example.a04_deber01.data.model.Recipe
import kotlinx.coroutines.flow.Flow

class RecipeRepository(private val recipeDao: RecipeDao) {

    fun getAllRecipes(): Flow<List<Recipe>> = recipeDao.getAllRecipes()

    suspend fun getRecipeById(recipeId: Int): Recipe? = recipeDao.getRecipeById(recipeId)

    suspend fun insertRecipe(recipe: Recipe) {
        recipeDao.insertRecipe(recipe)
    }

    suspend fun updateRecipe(recipe: Recipe) {
        recipeDao.updateRecipe(recipe)
    }

    suspend fun updateRecipeLocation(recipeId: Int, latitude: Double, longitude: Double) {
        recipeDao.updateRecipeLocation(recipeId, latitude, longitude) // ✅ Método para actualizar coordenadas
    }

    suspend fun deleteRecipe(recipe: Recipe) {
        recipeDao.deleteRecipe(recipe)
    }
}
