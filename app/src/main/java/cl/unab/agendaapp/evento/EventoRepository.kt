package cl.unab.agendaapp.evento

import cl.unab.agendaapp.util.GeneradorId

object EventoRepository {
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
        )
    )

    fun agregarEvento(evento: Evento) {
        eventos.add(evento)
    }

    fun obtenerEventoId(id: String): Evento? {
        return eventos.find { it.id == id }
    }

    fun obtenerEventos(): List<Evento> {
        return eventos.toList()
    }
}