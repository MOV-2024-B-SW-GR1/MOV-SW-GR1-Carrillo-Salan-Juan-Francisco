import Utils.ObservableList
import models.Receta
import services.RecetaService

class RecetaController(private val service: RecetaService) {
    val recetas = ObservableList<Receta>().apply {
        setAll(service.leerRecetas())
    }

    fun agregarReceta(receta: Receta) {
        service.crearReceta(receta)
        recetas.setAll(service.leerRecetas())
    }

    fun actualizarReceta(id: Int, receta: Receta) {
        service.actualizarReceta(
            id,
            receta.nombre,
            receta.tiempoPreparacion.toDouble(),
            receta.vegana
        )
        recetas.setAll(service.leerRecetas())
    }

    fun eliminarReceta(id: Int) {
        service.eliminarReceta(id)
        recetas.setAll(service.leerRecetas())
    }
}
