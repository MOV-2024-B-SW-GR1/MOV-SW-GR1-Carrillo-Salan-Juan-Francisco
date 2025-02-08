package com.example.a04_deber01.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a04_deber01.data.model.Recipe
import com.example.a04_deber01.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class RecipeViewModel(private val repository: RecipeRepository) : ViewModel() {

    val allRecipes: Flow<List<Recipe>> = repository.getAllRecipes()

    fun getRecipeById(recipeId: Int, callback: (Recipe?) -> Unit) {
        viewModelScope.launch {
            val recipe = repository.getRecipeById(recipeId)
            callback(recipe)
        }
    }

    fun insertRecipe(recipe: Recipe) = viewModelScope.launch {
        repository.insertRecipe(recipe)
    }

    fun updateRecipe(recipe: Recipe) = viewModelScope.launch {
        repository.updateRecipe(recipe)
    }

    fun deleteRecipe(recipe: Recipe) = viewModelScope.launch {
        repository.deleteRecipe(recipe)
    }
}
