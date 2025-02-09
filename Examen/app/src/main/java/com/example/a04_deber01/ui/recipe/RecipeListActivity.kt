package com.example.a04_deber01.ui.recipe

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a04_deber01.R
import com.example.a04_deber01.data.database.AppDatabase
import com.example.a04_deber01.data.model.Recipe
import com.example.a04_deber01.databinding.ActivityRecipeListBinding
import com.example.a04_deber01.repository.RecipeRepository
import com.example.a04_deber01.ui.ingredient.IngredientListActivity
import com.example.a04_deber01.ui.map.MapActivity
import com.example.a04_deber01.ui.recipe.adapter.RecipeAdapter
import com.example.a04_deber01.utils.showRecipeDialog
import com.example.a04_deber01.utils.showUpdateRecipeDialog
import com.example.a04_deber01.viewmodel.RecipeViewModel
import kotlinx.coroutines.launch

class RecipeListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecipeListBinding
    private lateinit var adapter: RecipeAdapter
    private val database by lazy { AppDatabase.getDatabase(this) }
    private val repository by lazy { RecipeRepository(database.recipeDao()) }
    private val viewModel: RecipeViewModel by viewModels { RecipeViewModelFactory(repository) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupFab()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_open_map -> {
                viewModel.getRecipeById(1) { recipe -> // Mostrar la ubicación de la primera receta como ejemplo
                    recipe?.let {
                        val intent = MapActivity.newIntent(this, it.latitude, it.longitude)
                        startActivity(intent)
                    }
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupRecyclerView() {
        adapter = RecipeAdapter(
            onItemClick = { recipe ->
                startActivity(IngredientListActivity.newIntent(this, recipe.id))
            },
            onDeleteClick = { recipe ->
                viewModel.deleteRecipe(recipe)
                refreshData()
            },
            onEditClick = { recipe ->
                showUpdateRecipeDialog(this, recipe) { name, prepTime, servings ->
                    viewModel.updateRecipe(
                        recipe.copy(name = name, prepTime = prepTime, servings = servings)
                    )
                    refreshData()
                }
            },
            onViewMapClick = { recipe ->
                val intent = MapActivity.newIntent(this, recipe.latitude, recipe.longitude)
                startActivity(intent)
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
            viewModel.allRecipes.collect { recipeList ->
                adapter.submitList(recipeList)
            }
        }
    }

    private fun setupFab() {
        binding.fabRecipe.setOnClickListener {
            showRecipeDialog(this) { name, prepTime, servings ->
                viewModel.insertRecipe(
                    Recipe(name = name, prepTime = prepTime, servings = servings, latitude = 0.0, longitude = 0.0)
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
