package com.example.prova_parcial_mobile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.prova_parcial_mobile.data.receitas
import com.example.prova_parcial_mobile.databinding.ActivityListaReceitasBinding
import com.example.prova_parcial_mobile.databinding.ItemReceitaBinding
import com.example.prova_parcial_mobile.model.Receita

class ListaReceitasActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListaReceitasBinding
    private lateinit var adapter: ReceitaListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityListaReceitasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ReceitaListAdapter(receitas)
        binding.listaReceitas.adapter = adapter
        binding.listaReceitas.layoutManager = LinearLayoutManager(this)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // Ao voltar da tela de detalhe, alguma receita pode ter sido marcada como concluída
    override fun onResume() {
        super.onResume()
        adapter.atualizarLista(receitas)
    }
}

class ReceitaListAdapter(private var list: List<Receita>) :
    RecyclerView.Adapter<ReceitaListAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemReceitaBinding) : RecyclerView.ViewHolder(binding.root) {
        val context: Context = binding.root.context
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ItemReceitaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val receita = list[position]
        holder.binding.nomeReceita.text = receita.nome
        holder.binding.tempoPreparo.text =
            holder.context.getString(R.string.tempo_preparo, receita.tempoFormatado)
        // imagemRes é opcional: sem imagem própria, usa o ícone padrão
        holder.binding.imagemReceita.setImageResource(
            receita.imagemRes ?: R.drawable.ic_receita_placeholder
        )
        holder.binding.seloConcluida.seloTexto.text = holder.context.getString(R.string.selo_concluida)
        holder.binding.seloConcluida.root.visibility =
            if (receita.concluida) View.VISIBLE else View.GONE

        holder.binding.root.setOnClickListener {
            val intent = Intent(holder.context, DetalheReceitaActivity::class.java)
            intent.putExtra(DetalheReceitaActivity.EXTRA_RECEITA_ID, receita.id)
            holder.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = list.size

    // Compara item a item (equals da data class) e só redesenha o que mudou
    fun atualizarLista(novaLista: List<Receita>) {
        val listaAntiga = list
        list = novaLista
        novaLista.forEachIndexed { posicao, receita ->
            if (receita != listaAntiga.getOrNull(posicao)) notifyItemChanged(posicao)
        }
    }
}
