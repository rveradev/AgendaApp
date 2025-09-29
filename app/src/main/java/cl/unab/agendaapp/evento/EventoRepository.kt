package cl.unab.agendaapp.evento

import cl.unab.agendaapp.util.GeneradorId
import java.text.SimpleDateFormat
import java.util.Locale

object EventoRepository {
    private val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    // Muestra el uso de estructura de datos List
    private var eventos: MutableList<Evento> = mutableListOf(
        Evento(
            GeneradorId.obtenerId(),
            "Ejemplo primer evento",
            "01/01/2025",
            "Esta es una descripcion de ejemplo para el primer evento"
        ),
        Evento(
            GeneradorId.obtenerId(),
            "Ejemplo segundo evento",
            "10/10/2025",
            "Esta es una descripcion de ejemplo para el segundo evento"
        ),
        Evento(
            GeneradorId.obtenerId(),
            "Ejemplo tercer evento",
            "12/01/2025",
            null
        ),
        Evento(
            GeneradorId.obtenerId(),
            "A",
            "10/10/2000",
            "A"
        ),
        Evento(
            GeneradorId.obtenerId(),
            "Z",
            "10/07/2014",
            null
        ),
    )

    fun agregarEvento(evento: Evento) {
        eventos.add(evento)
    }

    fun obtenerEventoId(id: String): Evento? {
        return eventos.find { it.id == id }
    }

    fun obtenerEventos(sortBy: String): List<Evento> {
        // Muestra el uso de 'sortedBy' con una función lambda.
        return when (sortBy) {
            "titulo" -> eventos.sortedWith(compareBy { it.titulo })
            "fecha" -> eventos.sortedBy { dateFormat.parse(it.fecha) }
            else -> eventos
        }
    }
}