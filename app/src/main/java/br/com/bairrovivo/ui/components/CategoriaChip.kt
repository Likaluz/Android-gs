package br.com.bairrovivo.ui.components

import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import br.com.bairrovivo.model.Categoria

@Composable
fun CategoriaChip(categoria: Categoria, selecionada: Boolean, onClick: () -> Unit) {
    FilterChip(selected = selecionada, onClick = onClick, label = { Text(categoria.titulo) })
}
