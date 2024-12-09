package models

data class Receta(
    val id: Int,
    var nombre: String,
    var numIngredientes: Int,
    var tiempoPreparacion: Int, // En minutos
    var vegana: Boolean,
    val ingredientes: MutableList<Ingrediente>
)
