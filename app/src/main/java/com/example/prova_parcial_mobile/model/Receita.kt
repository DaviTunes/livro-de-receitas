package com.example.prova_parcial_mobile.model

import androidx.annotation.DrawableRes

data class Receita(
    val id: String,
    val nome: String,
    val tempoPreparoMinutos: Int,
    val ingredientes: List<Ingrediente>,
    val modoPreparo: List<String>,
    val concluida: Boolean = false,
    // Opcionais: nem toda receita tem dica ou imagem própria
    val dica: String? = null,
    @param:DrawableRes val imagemRes: Int? = null
) {
    init {
        require(tempoPreparoMinutos > 0) { "tempoPreparoMinutos deve ser maior que 0" }
        require(ingredientes.isNotEmpty()) { "a receita precisa de pelo menos um ingrediente" }
    }

    // Ex.: 40 -> "40 min", 90 -> "1 h 30 min", 120 -> "2 h"
    val tempoFormatado: String
        get() {
            val horas = tempoPreparoMinutos / 60
            val minutos = tempoPreparoMinutos % 60
            return when {
                horas == 0 -> "$minutos min"
                minutos == 0 -> "$horas h"
                else -> "$horas h $minutos min"
            }
        }
}
