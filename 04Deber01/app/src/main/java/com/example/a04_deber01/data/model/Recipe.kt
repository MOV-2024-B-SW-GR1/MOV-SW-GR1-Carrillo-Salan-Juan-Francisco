package com.example.a04_deber01.data.model

data class Recipe(
    val id: Int,
    val name: String,
    val prepTime: Int, // in minutes
    val servings: Int
)