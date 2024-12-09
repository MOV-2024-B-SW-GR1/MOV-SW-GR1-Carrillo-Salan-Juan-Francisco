package repositories

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import models.Receta
import java.io.File

class RecetaRepository(private val filePath: String) {
    private val objectMapper = jacksonObjectMapper()

    fun guardarRecetas(recetas: List<Receta>) {
        File(filePath).writeText(objectMapper.writeValueAsString(recetas))
    }

    fun cargarRecetas(): MutableList<Receta> {
        return if (File(filePath).exists()) {
            objectMapper.readValue(File(filePath).readText())
        } else {
            mutableListOf()
        }
    }
}
