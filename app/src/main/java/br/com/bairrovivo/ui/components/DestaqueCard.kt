package br.com.bairrovivo.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import br.com.bairrovivo.model.Iniciativa

@Composable
fun DestaqueCard(iniciativa: Iniciativa, onClick: () -> Unit) {
    Card(onClick = onClick, modifier = Modifier.width(252.dp), shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)) {
        Image(painterResource(iniciativa.imagem), contentDescription = "Ilustração de ${iniciativa.titulo}",
            modifier = Modifier.fillMaxWidth().height(118.dp), contentScale = ContentScale.Crop)
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(5.dp)) {
            Text(iniciativa.categorias.first().titulo.uppercase(),
                style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary)
            Text(iniciativa.titulo, style = MaterialTheme.typography.titleMedium)
            Text(iniciativa.resumo, style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant)
            Spacer(Modifier.height(2.dp))
            Text("Conhecer iniciativa →", style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary)
        }
    }
}
