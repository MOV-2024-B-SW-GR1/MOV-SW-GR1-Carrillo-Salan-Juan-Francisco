class MainView(private val controller: RecetaController) {

    fun show() {
        while (true) {
            println("\n=== Gestor de Recetas ===")
            println("1. Listar Recetas")
            println("2. Agregar Receta")
            println("3. Actualizar Receta")
            println("4. Eliminar Receta")
            println("5. Salir")
            print("Seleccione una opción: ")

            when (readLine()?.toIntOrNull()) {
                1 -> listRecipes()
                2 -> addRecipe()
                3 -> updateRecipe()
                4 -> deleteRecipe()
                5 -> {
                    println("Saliendo del programa...")
                    return
                }
                else -> println("Opción no válida. Por favor, intente de nuevo.")
            }
        }
    }

    private fun listRecipes() {
        println("\n=== Lista de Recetas ===")
        val recetas = controller.recetas.toList()
        if (recetas.isEmpty()) {
            println("No hay recetas registradas.")
        } else {
            println("+----+--------------------+----------------+--------+")
            println("| ID | Nombre             | Ingredientes   | Tiempo |")
            println("+----+--------------------+----------------+--------+")
            recetas.forEach { receta ->
                println(
                    "| ${receta.id.toString().padEnd(2)} | ${receta.nombre.padEnd(18)} | ${receta.numIngredientes.toString().padEnd(14)} | ${receta.tiempoPreparacion.toString().padEnd(6)} |"
                )
            }
            println("+----+--------------------+----------------+--------+")
        }
    }

    private fun addRecipe() {
        val formView = RecetaFormView(controller)
        formView.show()
    }

    private fun updateRecipe() {
        print("\nIngrese el ID de la receta a actualizar: ")
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
        print("\nIngrese el ID de la receta a eliminar: ")
        val id = readLine()?.toIntOrNull()
        if (id != null) {
            controller.eliminarReceta(id)
            println("Receta eliminada con éxito.")
        } else {
            println("ID no válido.")
        }
    }
}
