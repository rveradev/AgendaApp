package cl.unab.agendaapp.evento

import cl.unab.agendaapp.util.GeneradorId

data class Evento(
    val id: String,
    val titulo: String,
    val fecha: String,
    // Implementación de Null Safety para indicar que la descripción puede ser nula.
    val descripcion: String?
)
