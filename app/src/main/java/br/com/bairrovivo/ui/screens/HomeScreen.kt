package br.com.bairrovivo.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.com.bairrovivo.data.IniciativaRepository
import br.com.bairrovivo.model.Categoria
import br.com.bairrovivo.model.Iniciativa
import br.com.bairrovivo.ui.components.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val repository = remember { IniciativaRepository() }
    var categoria by remember { mutableStateOf(Categoria.TODAS) }
    var consulta by remember { mutableStateOf("") }
    var selecionada by remember { mutableStateOf<Iniciativa?>(null) }
    var sobre by remember { mutableStateOf(false) }
    val iniciativas = remember(categoria, consulta) { repository.filtrar(categoria, consulta) }
    val destaques = remember { repository.obterDestaques() }

    if (selecionada != null) {
        BackHandler { selecionada = null }
        DetailScreen(iniciativa = selecionada!!, onBack = { selecionada = null })
        return
    }
    if (sobre) {
        AlertDialog(onDismissRequest = { sobre = false },
            title = { Text("Tecnologia que aproxima") },
            text = { Text("Bairro Vivo é um MVP CitizenTech para facilitar a descoberta de iniciativas comunitárias.\n\nTodos os serviços, locais e horários são fictícios. Os dados ficam no aplicativo e podem ser consultados sem internet.\n\nNão há inscrições, reservas ou atendimento real.\n\nLucas Domingues • RM 553304\nGlobal Solution 2026 • FIAP") },
            confirmButton = { TextButton(onClick = { sobre = false }) { Text("Entendi") } })
    }
    Scaffold(containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(title = {
                Text("bairro vivo", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            }, actions = {
                IconButton(onClick = { sobre = true }) { Icon(Icons.Default.Info, "Sobre o aplicativo") }
            }, colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background))
        }) { padding ->
        Box(Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.TopCenter) {
            LazyColumn(Modifier.widthIn(max = 840.dp).fillMaxWidth().testTag("lista_principal"),
                contentPadding = PaddingValues(bottom = 28.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)) {
                item(key = "introducao") {
                    Column(Modifier.padding(horizontal = 20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        Text("Pequenas conexões.\nUm bairro melhor.", style = MaterialTheme.typography.headlineLarge,
                            fontWeight = FontWeight.Bold)
                        Text("Descubra iniciativas que cuidam das pessoas e transformam a comunidade.",
                            style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        Surface(color = MaterialTheme.colorScheme.primaryContainer,
                            shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)) {
                            Text("CATÁLOGO DEMONSTRATIVO  •  DISPONÍVEL OFFLINE",
                                Modifier.padding(horizontal = 12.dp, vertical = 10.dp),
                                style = MaterialTheme.typography.labelSmall)
                        }
                    }
                }
                item(key = "busca") {
                    OutlinedTextField(value = consulta, onValueChange = { consulta = it },
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                        label = { Text("Busque uma iniciativa ou bairro") },
                        leadingIcon = { Icon(Icons.Default.Search, null) }, singleLine = true,
                        shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp))
                }
                item(key = "categorias") {
                    LazyRow(contentPadding = PaddingValues(horizontal = 20.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        items(Categoria.entries, key = { it.name }) { item ->
                            CategoriaChip(item, item == categoria, onClick = { categoria = item })
                        }
                    }
                }
                item(key = "titulo_destaques") {
                    SectionTitle("Boas ideias em destaque", "Conheça quem faz a diferença")
                }
                item(key = "destaques") {
                    LazyRow(contentPadding = PaddingValues(horizontal = 20.dp),
                        horizontalArrangement = Arrangement.spacedBy(14.dp)) {
                        items(destaques, key = { it.id }) { item ->
                            DestaqueCard(item, onClick = { selecionada = item })
                        }
                    }
                }
                item(key = "titulo_lista") {
                    SectionTitle(if (categoria == Categoria.TODAS) "Explore a comunidade" else categoria.titulo,
                        "${iniciativas.size} ${if (iniciativas.size == 1) "iniciativa encontrada" else "iniciativas encontradas"}")
                }
                if (iniciativas.isEmpty()) {
                    item(key = "vazio") {
                        Column(Modifier.fillMaxWidth().padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("Nenhuma iniciativa encontrada", style = MaterialTheme.typography.titleMedium)
                            Text("Experimente outra categoria ou palavra.", Modifier.padding(top = 8.dp))
                            TextButton(onClick = { consulta = ""; categoria = Categoria.TODAS }) { Text("Limpar filtros") }
                        }
                    }
                }
                items(iniciativas, key = { "iniciativa_${it.id}" }) { item ->
                    IniciativaCard(item, onClick = { selecionada = item }, modifier = Modifier.padding(horizontal = 20.dp))
                }
                item(key = "rodape") {
                    Text("Feito para aproximar.\nDados fictícios para demonstração acadêmica.",
                        Modifier.padding(horizontal = 20.dp), style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
        }
    }
}

@Composable
private fun SectionTitle(titulo: String, subtitulo: String) {
    Column(Modifier.padding(horizontal = 20.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(titulo, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        Text(subtitulo, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}
