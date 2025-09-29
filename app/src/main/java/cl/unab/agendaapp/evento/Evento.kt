package cl.unab.agendaapp.evento

import cl.unab.agendaapp.util.GeneradorId

data class Evento(
    val id: String,
    val titulo: String,
    val fecha: String,
    val descripcion: String?
)
