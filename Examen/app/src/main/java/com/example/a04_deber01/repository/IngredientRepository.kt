package com.example.a04_deber01.repository

import com.example.a04_deber01.data.dao.IngredientDao
import com.example.a04_deber01.data.model.Ingredient
import kotlinx.coroutines.flow.Flow

class IngredientRepository(private val ingredientDao: IngredientDao) {

    fun getIngredientsByRecipe(recipeId: Int): Flow<List<Ingredient>> = ingredientDao.getIngredientsByRecipe(recipeId)

    suspend fun getIngredientById(ingredientId: Int): Ingredient? = ingredientDao.getIngredientById(ingredientId)

    suspend fun insertIngredient(ingredient: Ingredient) {
        ingredientDao.insertIngredient(ingredient)
    }

    suspend fun updateIngredient(ingredient: Ingredient) {
        ingredientDao.updateIngredient(ingredient)
    }

    suspend fun deleteIngredient(ingredient: Ingredient) {
        ingredientDao.deleteIngredient(ingredient)
    }
}
