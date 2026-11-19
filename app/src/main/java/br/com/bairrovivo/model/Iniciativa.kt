package br.com.bairrovivo.model

import androidx.annotation.DrawableRes

data class Iniciativa(
    val id: Int,
    val titulo: String,
    val resumo: String,
    val descricao: String,
    val local: String,
    val horario: String,
    val acesso: String,
    val categorias: List<Categoria>,
    val destaque: Boolean,
    @DrawableRes val imagem: Int
)
