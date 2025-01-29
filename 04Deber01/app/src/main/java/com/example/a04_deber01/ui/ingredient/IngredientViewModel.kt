package com.example.a04_deber01.ui.ingredient

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a04_deber01.data.model.Ingredient
import com.example.a04_deber01.repository.IngredientRepository
import kotlinx.coroutines.launch

class IngredientViewModel(
    private val repository: IngredientRepository,
    private val recipeId: Int
) : ViewModel() {

    suspend fun getIngredients() = repository.getIngredientsByRecipe(recipeId)

    fun insertIngredient(ingredient: Ingredient, recipeId: Int) = viewModelScope.launch {
        repository.insertIngredient(ingredient)
    }

    fun updateIngredient(ingredient: Ingredient, recipeId: Int) = viewModelScope.launch {
        repository.updateIngredient(ingredient)
    }

    fun deleteIngredient(ingredientId: Int, recipeId: Int) = viewModelScope.launch {
        repository.deleteIngredient(ingredientId)
    }
}
