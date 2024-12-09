package models

data class Ingrediente(
    val id: Int,
    var nombre: String,
    var cantidad: Double, // Cantidad en gramos
    var unidad: String,
    var alergeno: Boolean
)
