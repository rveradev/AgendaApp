package cl.unab.agendaapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import cl.unab.agendaapp.databinding.ActivityDetalleEventoBinding
import cl.unab.agendaapp.evento.EventoRepository

class DetalleEventoActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_EVENT_ID = "extra_event_id"
    }

    private lateinit var binding: ActivityDetalleEventoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalleEventoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val eventId = intent.getStringExtra(EXTRA_EVENT_ID)
        cargarDetalleEvento(eventId)
    }

    private fun cargarDetalleEvento(id: String?) {
        id?.let {
            EventoRepository.obtenerEventoId(it)?.let {
                binding.apply {
                    txtTitulo.text = it.titulo
                    txtFecha.text = it.fecha
                    txtDescripcion.text = it.descripcion ?: "-- Sin descripcion. --"
                }

                with(supportActionBar) {
                    this?.title = it.titulo
                }
            } ?: run {
                binding.txtTitulo.text = "Error: Evento no encontrado con ID ${it}."
                supportActionBar?.title = "Error"
            }
        } ?: run {
            binding.txtTitulo.text = "Error: No se proporcionó ID de evento."
            supportActionBar?.title = "Error"
        }
    }
}