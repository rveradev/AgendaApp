package cl.unab.agendaapp.evento

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.unab.agendaapp.databinding.ItemEventoBinding
import cl.unab.agendaapp.util.GeneradorId

class EventoAdapter(
    private val onEventoClick: (Evento) -> Unit
) : RecyclerView.Adapter<EventoAdapter.EventoViewHolder>() {

    private var eventos: List<Evento> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventoViewHolder {
        val binding = ItemEventoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventoViewHolder, position: Int) {
        holder.bind(eventos[position], onEventoClick)
    }

    override fun getItemCount(): Int = eventos.size

    fun actualizarLista(nuevaLista: List<Evento>) {
        eventos = nuevaLista.apply {
            notifyDataSetChanged()
        }
    }

    class EventoViewHolder(private val binding: ItemEventoBinding) : RecyclerView.ViewHolder(binding.root) {
        private lateinit var identificadorJava: GeneradorId

        fun bind(evento: Evento, onEventoClick: (Evento) -> Unit) {
            identificadorJava = GeneradorId()
            binding.txtEventoTitulo.text = evento.titulo
            binding.txtxEventoFecha.text = evento.fecha

            binding.root.also { view ->
                view.setOnClickListener {
                    onEventoClick(evento)
                }
            }
        }
    }
}