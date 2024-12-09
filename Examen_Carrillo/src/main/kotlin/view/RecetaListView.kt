import models.Receta

class RecetaListView(private val controller: RecetaController) {

    fun show() {
        println("=== Listado de Recetas ===")

        val recetas = controller.recetas.toList()
        if (recetas.isEmpty()) {
            println("No hay recetas registradas.")
            return
        }

        // Print table header
        println("+----+--------------------+----------------+--------+")
        println("| ID | Nombre             | Ingredientes   | Tiempo |")
        println("+----+--------------------+----------------+--------+")

        // Print each recipe in a table row
        recetas.forEach { receta ->
            println(
                "| ${receta.id.toString().padEnd(2)} | ${receta.nombre.padEnd(18)} | ${receta.numIngredientes.toString().padEnd(14)} | ${receta.tiempoPreparacion.toString().padEnd(6)} |"
            )
        }
        println("+----+--------------------+----------------+--------+")

        // Allow the user to choose an action
        println("\nOpciones:")
        println("1. Editar receta")
        println("2. Eliminar receta")
        println("3. Volver al menú principal")
        print("Seleccione una opción: ")

        when (readLine()?.toIntOrNull()) {
            1 -> editRecipe()
            2 -> deleteRecipe()
            3 -> return
            else -> println("Opción no válida.")
        }
    }

    private fun editRecipe() {
        print("Ingrese el ID de la receta a editar: ")
        val id = readLine()?.toIntOrNull()
        val receta = controller.recetas.toList().find { it.id == id }
        if (receta != null) {
            val formView = RecetaFormView(controller)
            formView.show(receta)
        } else {
            println("Receta no encontrada.")
        }
    }

    private fun deleteRecipe() {
        print("Ingrese el ID de la receta a eliminar: ")
        val id = readLine()?.toIntOrNull()
        if (id != null) {
            controller.eliminarReceta(id)
            println("Receta eliminada con éxito.")
        } else {
            println("ID no válido.")
        }
    }
}
