package com.example.prova_parcial_mobile

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.prova_parcial_mobile.data.receitas
import com.example.prova_parcial_mobile.databinding.ActivityDetalheReceitaBinding
import com.example.prova_parcial_mobile.databinding.IngredienteLayoutBinding
import com.example.prova_parcial_mobile.databinding.SeloLayoutBinding
import com.example.prova_parcial_mobile.model.Ingrediente
import com.example.prova_parcial_mobile.model.Receita

class DetalheReceitaActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_RECEITA_ID = "receita_id"
    }

    private lateinit var binding: ActivityDetalheReceitaBinding
    private lateinit var receita: Receita

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetalheReceitaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // getStringExtra retorna String? e o find pode não achar nada: tratamos o null
        val receitaId = intent.getStringExtra(EXTRA_RECEITA_ID)
        val receitaEncontrada = receitas.find { it.id == receitaId }
        if (receitaEncontrada == null) {
            Toast.makeText(this, R.string.receita_nao_encontrada, Toast.LENGTH_SHORT).show()
            finish()
            return
        }
        receita = receitaEncontrada

        binding.nomeReceita.text = receita.nome
        binding.imagemReceita.setImageResource(receita.imagemRes ?: R.drawable.ic_receita_placeholder)
        binding.modoPreparo.text = receita.modoPreparo
            .mapIndexed { indice, passo -> getString(R.string.passo_item, indice + 1, passo) }
            .joinToString("\n")

        addSeloView(binding.selos, getString(R.string.tempo_preparo, receita.tempoFormatado))
        addSeloView(binding.selos, getString(R.string.qtd_ingredientes, receita.ingredientes.size))

        receita.ingredientes.forEach { ingrediente ->
            addIngredienteView(binding.listaIngredientes, ingrediente)
        }

        // dica é opcional: o card só aparece quando ela existe
        receita.dica?.let {
            binding.dica.text = it
            binding.cardDica.visibility = View.VISIBLE
        }

        binding.checkboxConcluida.isChecked = receita.concluida
        updateStatusViews()

        binding.checkboxConcluida.setOnCheckedChangeListener { _, isChecked ->
            receita = receita.copy(concluida = isChecked)
            // Troca a receita antiga pela nova na lista mock, para a tela de lista enxergar a mudança
            receitas = receitas.map { if (it.id == receita.id) receita else it }
            updateStatusViews()
        }
    }

    private fun addSeloView(atRow: LinearLayout, text: String) {
        val seloBinding = SeloLayoutBinding.inflate(layoutInflater, atRow, false)
        seloBinding.seloTexto.text = text
        atRow.addView(seloBinding.root)
    }

    private fun addIngredienteView(atLayout: LinearLayout, ingrediente: Ingrediente) {
        val ingredienteBinding = IngredienteLayoutBinding.inflate(layoutInflater, atLayout, false)
        ingredienteBinding.nomeIngrediente.text = ingrediente.nome
        ingredienteBinding.quantidadeIngrediente.text = ingrediente.quantidade
        atLayout.addView(ingredienteBinding.root)
    }

    private fun updateStatusViews() {
        if (receita.concluida) {
            binding.statusReceita.text = getString(R.string.status_concluida)
            binding.statusReceita.setTextColor(ContextCompat.getColor(this, R.color.verde_concluida))
        } else {
            binding.statusReceita.text = getString(R.string.status_pendente)
            binding.statusReceita.setTextColor(ContextCompat.getColor(this, R.color.status_pendente))
        }
    }
}
