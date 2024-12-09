import models.Receta
import models.Ingrediente
import services.RecetaService
import java.time.LocalDate

class RecetaFormView(private val controller: RecetaController) {

    fun show(receta: Receta? = null) {
        println("=== Formulario de Receta ===")

        // Retrieve existing recipe or create a new one
        val existingReceta = receta ?: Receta(
            id = (controller.recetas.toList().size + 1),
            nombre = "",
            numIngredientes = 0,
            tiempoPreparacion = 0,
            vegana = false,
            ingredientes = mutableListOf()
        )

        // Prompt for recipe name
        print("Nombre [${existingReceta.nombre}]: ")
        val nombre = readLine()?.takeIf { it.isNotBlank() } ?: existingReceta.nombre

        // Prompt for preparation time
        print("Tiempo de preparación (minutos) [${existingReceta.tiempoPreparacion}]: ")
        val tiempoPreparacion = readLine()?.toIntOrNull() ?: existingReceta.tiempoPreparacion

        // Prompt for vegan status
        print("¿Es vegana? (true/false) [${existingReceta.vegana}]: ")
        val vegana = readLine()?.toBooleanStrictOrNull() ?: existingReceta.vegana

        // Prompt for ingredients
        val ingredientes = if (receta == null || existingReceta.ingredientes.isEmpty()) {
            println("=== Ingredientes ===")
            mutableListOf<Ingrediente>().apply {
                while (true) {
                    println("Agregar un nuevo ingrediente (deje vacío para terminar):")
                    print("Nombre: ")
                    val nombreIngrediente = readLine().orEmpty().takeIf { it.isNotBlank() } ?: break
                    print("Cantidad (en gramos): ")
                    val cantidad = readLine()?.toDoubleOrNull() ?: 0.0
                    print("¿Es alérgeno? (true/false): ")
                    val alergeno = readLine()?.toBooleanStrictOrNull() ?: false

                    add(
                        Ingrediente(
                            id = size + 1,
                            nombre = nombreIngrediente,
                            cantidad = cantidad,
                            unidad = "gramos",
                            alergeno = alergeno
                        )
                    )
                }
            }
        } else {
            existingReceta.ingredientes
        }

        val numIngredientes = ingredientes.size

        // Save or update the recipe
        val nuevaReceta = existingReceta.copy(
            nombre = nombre,
            tiempoPreparacion = tiempoPreparacion,
            vegana = vegana,
            ingredientes = ingredientes,
            numIngredientes = numIngredientes
        )

        if (receta == null) {
            controller.agregarReceta(nuevaReceta)
            println("Receta agregada con éxito.")
        } else {
            controller.actualizarReceta(existingReceta.id, nuevaReceta)
            println("Receta actualizada con éxito.")
        }
    }
}
