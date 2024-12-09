package services

import models.Ingrediente
import models.Receta
import repositories.RecetaRepository

class RecetaService(private val recetaRepository: RecetaRepository) {
    private val recetas = recetaRepository.cargarRecetas()

    fun crearReceta(receta: Receta) {
        recetas.add(receta)
        guardarCambios()
    }

    fun leerRecetas(): List<Receta> {
        return recetaRepository.cargarRecetas()
    }

    fun listarRecetas(): List<Receta> = recetas

    fun buscarRecetaPorId(id: Int): Receta? = recetas.find { it.id == id }

    fun actualizarReceta(id: Int, nombre: String?, tiempoPreparacion: Double?, vegana: Boolean?) {
        val receta = buscarRecetaPorId(id)
        if (receta != null) {
            nombre?.let { receta.nombre = it }
            tiempoPreparacion?.let { receta.tiempoPreparacion = it.toInt() }
            vegana?.let { receta.vegana = it }
            guardarCambios()
        }
    }

    fun eliminarReceta(id: Int) {
        recetas.removeIf { it.id == id }
        guardarCambios()
    }

    private fun guardarCambios() {
        recetaRepository.guardarRecetas(recetas)
    }
}
