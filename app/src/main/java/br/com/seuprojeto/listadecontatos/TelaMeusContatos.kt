package br.com.seuprojeto.listadecontatos

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.seuprojeto.listadecontatos.ui.theme.*
import kotlinx.coroutines.launch

@Composable
fun TelaMeusContatos(
    dao: ContatoDao,
    navController: NavController,
    onVoltarCadastro: () -> Unit
) {
    val listaContatos by dao.listarTodos().collectAsState(initial = emptyList())
    val coroutineScope = rememberCoroutineScope()

    LayoutPadrao(
        subtitulo = "Meus Contatos",
        botaoTexto = "Cadastrar Contato",
        onBotaoClique = { onVoltarCadastro() }
    ) {
        if (listaContatos.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Nenhum contato cadastrado.", color = Secundaria)
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(listaContatos) { contato ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(2.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(text = contato.nome, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                                if (contato.telefone.isNotBlank()) {
                                    Text(text = "Tel: ${contato.telefone}", fontSize = 14.sp, color = Color.DarkGray)
                                }
                                if (contato.email.isNotBlank()) {
                                    Text(text = "Email: ${contato.email}", fontSize = 14.sp, color = Color.DarkGray)
                                }
                            }

                            // botão edição
                            IconButton(onClick = {
                                navController.navigate("editar/${contato.id}")
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = "Editar Contato",
                                    tint = Color(0xFF4CAF50)
                                )
                            }

                            // botão exclusão
                            IconButton(onClick = {
                                coroutineScope.launch {
                                    dao.excluir(contato)
                                }
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Excluir Contato",
                                    tint = Color.Red
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}