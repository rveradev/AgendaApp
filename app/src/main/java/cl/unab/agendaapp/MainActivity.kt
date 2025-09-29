package cl.unab.agendaapp

import android.content.Intent
import android.os.Bundle
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

    private lateinit var binding: ActivityMainBinding
    private val eventoAdapter = EventoAdapter(::onEventoClick)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configurarRecyclerView()

        binding.btnAgregarEvento.setOnClickListener {
            startActivity(Intent(this, AgregarEventoActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        var eventos = EventoRepository.obtenerEventos()

        eventoAdapter.actualizarLista(eventos)
    }

    private fun configurarRecyclerView(){
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