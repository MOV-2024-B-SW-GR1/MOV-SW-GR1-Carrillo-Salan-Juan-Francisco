package com.example.a04_deber01.ui.recipe

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a04_deber01.data.model.Recipe
import com.example.a04_deber01.databinding.ActivityRecipeListBinding
import com.example.a04_deber01.repository.RecipeRepository
import com.example.a04_deber01.ui.ingredient.IngredientListActivity
import com.example.a04_deber01.ui.recipe.adapter.RecipeAdapter
import com.example.a04_deber01.utils.showRecipeDialog
import com.example.a04_deber01.utils.showUpdateRecipeDialog
import kotlinx.coroutines.launch

class RecipeListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecipeListBinding
    private val repository = RecipeRepository()
    private val viewModel = RecipeViewModel(repository)
    private lateinit var adapter: RecipeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupFab()
    }

    private fun setupRecyclerView() {
        adapter = RecipeAdapter(
            onItemClick = { recipe ->
                startActivity(
                    IngredientListActivity.newIntent(this, recipe.id)
                )
            },
            onDeleteClick = { recipeId ->
                viewModel.deleteRecipe(recipeId)
                refreshData()
            },
            onEditClick = { recipe ->
                showUpdateRecipeDialog(
                    context = this,
                    recipe = recipe
                ) { name, prepTime, servings ->
                    viewModel.updateRecipe(
                        recipe.copy(
                            name = name,
                            prepTime = prepTime,
                            servings = servings
                        )
                    )
                    refreshData()
                }
            }
        )

        binding.recyclerViewRecipes.apply {
            layoutManager = LinearLayoutManager(this@RecipeListActivity)
            adapter = this@RecipeListActivity.adapter
        }
        refreshData()
    }

    private fun refreshData() {
        lifecycleScope.launch {
            val recipes = repository.getAllRecipes()
            adapter.submitList(recipes)
        }
    }

    private fun setupFab() {
        binding.fabRecipe.setOnClickListener {
            showRecipeDialog(
                context = this
            ) { name, prepTime, servings ->
                viewModel.insertRecipe(
                    Recipe(
                        id = 0,
                        name = name,
                        prepTime = prepTime,
                        servings = servings
                    )
                )
                refreshData()
            }
        }
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, RecipeListActivity::class.java)
        }
    }
}
