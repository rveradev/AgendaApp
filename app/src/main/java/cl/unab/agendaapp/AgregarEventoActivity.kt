package cl.unab.agendaapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import cl.unab.agendaapp.databinding.ActivityAgregarEventoBinding
import cl.unab.agendaapp.evento.Evento
import cl.unab.agendaapp.evento.EventoRepository
import cl.unab.agendaapp.util.GeneradorId
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
class AgregarEventoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAgregarEventoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgregarEventoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Agregar Evento"

        binding.btnGuardar.setOnClickListener {
            guardarEvento()
        }

        binding.tilFecha.setEndIconOnClickListener {
            showMaterialDatePicker()
        }

    }

    private fun guardarEvento() {
        val titulo = binding.etTitulo.text.toString()
        val fecha = binding.etFecha.text.toString()
        val descripcion = binding.etDescripcion.text.toString().takeIf { it.isNotBlank() } // Uso de 'takeIf' para obtener nulo si está en blanco

        if (titulo.isBlank() || fecha.isBlank()) {
            Toast.makeText(this, "Título y Fecha son obligatorios.", Toast.LENGTH_SHORT).show()
            return
        }

        val eventoId = GeneradorId.obtenerId()

        val nuevoEvento = Evento(
            id = eventoId,
            titulo = titulo,
            fecha = fecha,
            descripcion = descripcion
        )

        EventoRepository.agregarEvento(nuevoEvento)
        Toast.makeText(this, "Evento '${nuevoEvento.titulo}' agregado.", Toast.LENGTH_SHORT).show()
        Toast.makeText(this, "ID: ${eventoId}", Toast.LENGTH_SHORT).show()
        finish()
    }

    private fun showMaterialDatePicker() {
        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Selecciona una fecha")
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .build()

        datePicker.addOnPositiveButtonClickListener { selection ->
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = selection

            val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            binding.etFecha.setText(format.format(calendar.time))
        }
        datePicker.show(supportFragmentManager, "DATE_PICKER")
    }
}