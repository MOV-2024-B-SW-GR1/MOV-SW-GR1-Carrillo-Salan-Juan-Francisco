package com.example.a04_deber01.ui.recipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a04_deber01.data.model.Recipe
import com.example.a04_deber01.repository.RecipeRepository
import kotlinx.coroutines.launch

class RecipeViewModel(
    private val repository: RecipeRepository
) : ViewModel() {

    suspend fun getRecipes() = repository.getAllRecipes()

    fun insertRecipe(recipe: Recipe) = viewModelScope.launch {
        repository.insertRecipe(recipe)
    }

    fun updateRecipe(recipe: Recipe) = viewModelScope.launch {
        repository.updateRecipe(recipe)
    }

    fun deleteRecipe(recipeId: Int) = viewModelScope.launch {
        repository.deleteRecipe(recipeId)
    }
}
