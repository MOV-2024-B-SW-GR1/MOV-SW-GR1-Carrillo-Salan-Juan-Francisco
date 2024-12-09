import models.Ingrediente
import models.Receta
import repositories.RecetaRepository
import services.RecetaService
import kotlin.system.exitProcess

fun main() {
    val recetaRepository = RecetaRepository("recetas.json")
    val recetaService = RecetaService(recetaRepository)

    while (true) {
        println("=== Gestión de Recetas ===")
        println("1. Crear Receta")
        println("2. Listar Recetas")
        println("3. Actualizar Receta")
        println("4. Eliminar Receta")
        println("5. Salir")
        print("Seleccione una opción: ")

        when (readln().toIntOrNull()) {
            1 -> {
                println("Creando nueva receta...")
                print("Nombre: ")
                val nombre = readln()
                print("Tiempo de preparación (minutos): ")
                val tiempoPreparacion = readln().toDoubleOrNull() ?: 0.0
                print("¿Es vegana? (true/false): ")
                val vegana = readln().toBooleanStrictOrNull() ?: false

                val ingredientes = mutableListOf<Ingrediente>()
                println("¿Cuántos ingredientes tiene la receta?")
                val numIngredientes = readln().toIntOrNull() ?: 0
                repeat(numIngredientes) {
                    print("Nombre del ingrediente: ")
                    val nombreIngrediente = readln()
                    print("Cantidad (gramos): ")
                    val cantidad = readln().toDoubleOrNull() ?: 0.0
                    print("Unidad (ej: gramos, litros): ")
                    val unidad = readln()
                    print("¿Es alérgeno? (true/false): ")
                    val alergeno = readln().toBooleanStrictOrNull() ?: false
                    ingredientes.add(Ingrediente(it + 1, nombreIngrediente, cantidad, unidad, alergeno))
                }

                recetaService.crearReceta(
                    Receta(
                        id = recetaService.listarRecetas().size + 1,
                        nombre = nombre,
                        numIngredientes = numIngredientes,
                        tiempoPreparacion = tiempoPreparacion.toInt(),
                        vegana = vegana,
                        ingredientes = ingredientes
                    )
                )
                println("Receta creada con éxito.")
            }

            2 -> {
                println("=== Listado de Recetas ===")
                recetaService.listarRecetas().forEach { println(it) }
            }

            3 -> {
                print("ID de la receta a actualizar: ")
                val id = readln().toIntOrNull()
                if (id != null) {
                    print("Nuevo nombre (dejar vacío para no cambiar): ")
                    val nombre = readln().takeIf { it.isNotEmpty() }
                    print("Nuevo tiempo de preparación (dejar vacío para no cambiar): ")
                    val tiempoPreparacion = readln().toDoubleOrNull()
                    print("¿Es vegana? (true/false, dejar vacío para no cambiar): ")
                    val vegana = readln().toBooleanStrictOrNull()
                    recetaService.actualizarReceta(id, nombre, tiempoPreparacion, vegana)
                    println("Receta actualizada con éxito.")
                }
            }

            4 -> {
                print("ID de la receta a eliminar: ")
                val id = readln().toIntOrNull()
                if (id != null) {
                    recetaService.eliminarReceta(id)
                    println("Receta eliminada con éxito.")
                }
            }

            5 -> {
                println("Saliendo...")
                exitProcess(0)
            }

            else -> println("Opción no válida, intente nuevamente.")
        }
    }
}
