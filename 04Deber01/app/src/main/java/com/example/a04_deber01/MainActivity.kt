package com.example.a04_deber01

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.a04_deber01.databinding.ActivityMainBinding
import com.example.a04_deber01.ui.recipe.RecipeListActivity


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Using ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Navigate to the list of recipes
        binding.btnIrRecetas.setOnClickListener {
            startActivity(Intent(this, RecipeListActivity::class.java))
        }
    }
}
