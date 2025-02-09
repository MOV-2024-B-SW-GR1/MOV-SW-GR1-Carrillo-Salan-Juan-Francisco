package com.example.a04_deber01.ui.ingredient

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a04_deber01.data.database.AppDatabase
import com.example.a04_deber01.data.model.Ingredient
import com.example.a04_deber01.databinding.ActivityIngredientListBinding
import com.example.a04_deber01.repository.IngredientRepository
import com.example.a04_deber01.utils.showIngredientDialog
import com.example.a04_deber01.utils.showUpdateIngredientDialog
import com.example.a04_deber01.ui.ingredient.adapter.IngredientAdapter
import kotlinx.coroutines.launch

class IngredientListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIngredientListBinding
    private lateinit var adapter: IngredientAdapter
    val database = AppDatabase.getDatabase(this)
    val repository = IngredientRepository(database.ingredientDao())
    private var recipeId: Int = 0
    val viewModel = IngredientViewModel(repository, recipeId)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIngredientListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recipeId = intent.getIntExtra(EXTRA_RECIPE_ID, 0)

        setupRecyclerView()
        setupFab()
    }

    private fun setupRecyclerView() {
        adapter = IngredientAdapter(
            onDeleteClick = { ingredient -> // ✅ Now receiving full Ingredient object
                viewModel.deleteIngredient(ingredient)
                refreshData()
            },
            onEditClick = { ingredient ->
                showUpdateIngredientDialog(
                    context = this,
                    ingredient = ingredient
                ) { name, quantity, unit ->
                    viewModel.updateIngredient(
                        ingredient.copy(
                            name = name,
                            quantity = quantity,
                            unit = unit
                        )
                    )
                    refreshData()
                }
            }
        )

        binding.recyclerViewIngredients.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewIngredients.adapter = adapter

        refreshData()
    }


    private fun setupFab() {
        binding.fabIngredient.setOnClickListener {
            showIngredientDialog(
                context = this
            ) { name, quantity, unit ->
                viewModel.insertIngredient(
                    Ingredient(
                        id = 0,
                        name = name,
                        quantity = quantity,
                        unit = unit,
                        recipeId
                    )
                )
                refreshData()
            }
        }
    }

    private fun refreshData() {
        lifecycleScope.launch {
            viewModel.ingredients.collect { ingredientList ->
                adapter.submitList(ingredientList)
            }
        }
    }

    companion object {
        private const val EXTRA_RECIPE_ID = "extra_recipe_id"

        fun newIntent(context: Context, recipeId: Int): Intent {
            return Intent(context, IngredientListActivity::class.java).apply {
                putExtra(EXTRA_RECIPE_ID, recipeId)
            }
        }
    }
}
