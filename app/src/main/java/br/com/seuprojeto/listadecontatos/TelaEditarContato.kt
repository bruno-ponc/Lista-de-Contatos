package br.com.seuprojeto.listadecontatos

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@Composable
fun TelaEditarContato(contatoId: Int, navController: NavController, database: AppDatabase) {
    val coroutineScope = rememberCoroutineScope()
    val dao = database.contatoDao()

    var nome by remember { mutableStateOf("") }
    var telefone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var contatoOriginal: Contato? by remember { mutableStateOf(null) }

    LaunchedEffect(contatoId) {
        val contato = dao.buscarPorId(contatoId)
        if (contato != null) {
            contatoOriginal = contato
            nome = contato.nome
            telefone = contato.telefone
            email = contato.email
        }
    }

    LayoutPadrao(
        subtitulo = "Editar Contato",
        botaoTexto = "Voltar para Lista",
        onBotaoClique = { navController.popBackStack() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            OutlinedTextField(
                value = nome,
                onValueChange = { nome = it },
                label = { Text("Nome") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = telefone,
                onValueChange = { novoTexto ->
                    if (novoTexto.all { it.isDigit() }) {
                        telefone = novoTexto
                    }
                },
                label = { Text("Telefone") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    if (contatoOriginal != null && nome.isNotBlank()) {
                        val contatoAtualizado = contatoOriginal!!.copy(
                            nome = nome,
                            telefone = telefone,
                            email = email
                        )
                        coroutineScope.launch {
                            dao.atualizar(contatoAtualizado)
                            navController.popBackStack()
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text("Salvar Alterações", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}