package com.example.a04_deber01.data.dao

import androidx.room.*
import com.example.a04_deber01.data.model.Recipe
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipes")
    fun getAllRecipes(): Flow<List<Recipe>>

    @Query("SELECT * FROM recipes WHERE id = :recipeId")
    suspend fun getRecipeById(recipeId: Int): Recipe?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: Recipe)

    @Update
    suspend fun updateRecipe(recipe: Recipe)

    @Query("UPDATE recipes SET latitude = :latitude, longitude = :longitude WHERE id = :recipeId")
    suspend fun updateRecipeLocation(recipeId: Int, latitude: Double, longitude: Double) // ✅ Método para actualizar ubicación

    @Delete
    suspend fun deleteRecipe(recipe: Recipe)
}
