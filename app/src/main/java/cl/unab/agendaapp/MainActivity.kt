package cl.unab.agendaapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import cl.unab.agendaapp.databinding.ActivityMainBinding
import cl.unab.agendaapp.evento.Evento
import cl.unab.agendaapp.evento.EventoAdapter
import cl.unab.agendaapp.evento.EventoRepository
import cl.unab.agendaapp.util.GeneradorId

class MainActivity : AppCompatActivity() {

    // Uso de 'lateinit' para una variable mutable no nula, inicializada más tarde (en onCreate).
    private lateinit var binding: ActivityMainBinding
    private val eventoAdapter = EventoAdapter(::onEventoClick)
    private var criterioOrden: String = "default"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configurarRecyclerView()

        configuracionSpinnerOrdenar()

        binding.btnAgregarEvento.setOnClickListener {
            startActivity(Intent(this, AgregarEventoActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        cargarEventos(criterioOrden)
    }

    private fun configuracionSpinnerOrdenar(){
        val opciones = arrayOf("Default", "Titulo", "Fecha")

        val spinnerAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            opciones
        ).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerOrdenar.adapter = this
        }

        binding.spinnerOrdenar.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                val selectedOption = opciones[position]
                val nuevoCriterio = when (selectedOption) {
                    "Título" -> "titulo"
                    "Fecha" -> "fecha"
                    else -> "default"
                }

                if (nuevoCriterio != criterioOrden) {
                    criterioOrden = nuevoCriterio
                    cargarEventos(criterioOrden)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }


    }

    private fun cargarEventos(sortBy: String){
        val eventos = EventoRepository.obtenerEventos(sortBy)
        eventos.let {
            eventoAdapter.actualizarLista(eventos)
        }
    }

    private fun configurarRecyclerView(){
        // Uso de 'with' para llamadas al mismo objeto.
        with(binding.rvEventos) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = eventoAdapter
            addItemDecoration(DividerItemDecoration(this@MainActivity, LinearLayoutManager.VERTICAL))
        }
    }

    private fun onEventoClick(evento: Evento) {
        val intent = Intent(this, DetalleEventoActivity::class.java).run {
            putExtra(DetalleEventoActivity.EXTRA_EVENT_ID, evento.id)
            this
        }
        startActivity(intent)
    }
}