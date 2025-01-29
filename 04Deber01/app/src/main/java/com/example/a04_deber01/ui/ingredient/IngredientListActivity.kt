package com.example.a04_deber01.ui.ingredient

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a04_deber01.data.model.Ingredient
import com.example.a04_deber01.databinding.ActivityIngredientListBinding
import com.example.a04_deber01.repository.IngredientRepository
import com.example.a04_deber01.utils.showIngredientDialog
import com.example.a04_deber01.utils.showUpdateIngredientDialog
import com.example.recipes.ui.ingredient.adapter.IngredientAdapter
import kotlinx.coroutines.launch

class IngredientListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIngredientListBinding
    private lateinit var adapter: IngredientAdapter
    private lateinit var viewModel: IngredientViewModel

    private var recipeId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIngredientListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recipeId = intent.getIntExtra(EXTRA_RECIPE_ID, 0)
        viewModel = IngredientViewModel(IngredientRepository(), recipeId)

        setupRecyclerView()
        setupFab()
    }

    private fun setupRecyclerView() {
        adapter = IngredientAdapter(
            onDeleteClick = { ingredientId ->
                viewModel.deleteIngredient(ingredientId, recipeId)
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
                        ),
                        recipeId
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
                        unit = unit
                    ),
                    recipeId
                )
                refreshData()
            }
        }
    }

    private fun refreshData() {
        lifecycleScope.launch {
            val ingredients = viewModel.getIngredients()
            adapter.submitList(ingredients)
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
