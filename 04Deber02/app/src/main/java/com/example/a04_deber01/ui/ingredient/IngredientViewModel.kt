package com.example.a04_deber01.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a04_deber01.data.model.Ingredient
import com.example.a04_deber01.repository.IngredientRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class IngredientViewModel(private val repository: IngredientRepository, private val recipeId: Int) : ViewModel() {

    val ingredients: Flow<List<Ingredient>> = repository.getIngredientsByRecipe(recipeId) // ✅ Keep only this

    fun getIngredientById(ingredientId: Int, callback: (Ingredient?) -> Unit) {
        viewModelScope.launch {
            val ingredient = repository.getIngredientById(ingredientId)
            callback(ingredient)
        }
    }

    fun insertIngredient(ingredient: Ingredient) = viewModelScope.launch {
        repository.insertIngredient(ingredient)
    }

    fun updateIngredient(ingredient: Ingredient) = viewModelScope.launch {
        repository.updateIngredient(ingredient)
    }

    fun deleteIngredient(ingredient: Ingredient) = viewModelScope.launch {
        repository.deleteIngredient(ingredient)
    }
}
