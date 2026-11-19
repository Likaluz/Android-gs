package br.com.bairrovivo.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.com.bairrovivo.model.Iniciativa
import br.com.bairrovivo.ui.components.InfoLine

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun DetailScreen(iniciativa: Iniciativa, onBack: () -> Unit) {
    Scaffold(containerColor = MaterialTheme.colorScheme.background, topBar = {
        TopAppBar(title = { Text("Conheça a iniciativa") }, navigationIcon = {
            IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, "Voltar") }
        }, colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background))
    }) { padding ->
        Box(Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.TopCenter) {
            Column(Modifier.widthIn(max = 760.dp).fillMaxWidth().verticalScroll(rememberScrollState())
                .padding(20.dp), verticalArrangement = Arrangement.spacedBy(18.dp)) {
                Image(painterResource(iniciativa.imagem), contentDescription = "Ilustração de ${iniciativa.titulo}",
                    modifier = Modifier.fillMaxWidth().height(210.dp), contentScale = ContentScale.Crop)
                FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    iniciativa.categorias.forEach { categoria ->
                        SuggestionChip(onClick = {}, enabled = false, label = { Text(categoria.titulo,
                            color = MaterialTheme.colorScheme.primary) })
                    }
                }
                Text(iniciativa.titulo, style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Bold)
                Text(iniciativa.resumo, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary)
                Text(iniciativa.descricao, style = MaterialTheme.typography.bodyLarge)
                Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)) {
                    Column(Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
                        Text("Como funciona", style = MaterialTheme.typography.titleMedium)
                        InfoLine(iniciativa.local, Icons.Default.Place)
                        InfoLine(iniciativa.horario, Icons.Default.Info)
                        InfoLine(iniciativa.acesso, Icons.Default.Info)
                    }
                }
                Text("Esta é uma iniciativa fictícia. Locais e horários são exemplos para demonstrar a solução. Não há inscrição ou atendimento real pelo aplicativo.",
                    style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Button(onClick = onBack, modifier = Modifier.fillMaxWidth()) { Text("Voltar para iniciativas") }
                Spacer(Modifier.height(12.dp))
            }
        }
    }
}
