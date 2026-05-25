package br.com.seuprojeto.listadecontatos

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.seuprojeto.listadecontatos.ui.theme.* 
import kotlinx.coroutines.launch

@Composable
fun TelaCadastro(dao: ContatoDao, onIrParaContatos: () -> Unit) {
    var nome by remember { mutableStateOf("") }
    var telefone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    LayoutPadrao(
        subtitulo = "Cadastrar Contato",
        botaoTexto = "Meus Contatos",
        onBotaoClique = { onIrParaContatos() }
    ) {
        OutlinedTextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text("Nome") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = telefone,
            onValueChange = { novoTexto ->
                if (novoTexto.all { it.isDigit() }) {
                    telefone = novoTexto
                }
            },
            label = { Text("Telefone") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("E-mail") },
            modifier = Modifier.fillMaxWidth().padding(6.dp)
        )

        Button(
            onClick = {
                if (nome.isBlank()) {
                    Toast.makeText(context, "O Nome é Obrigatório!", Toast.LENGTH_SHORT).show()
                } else if (telefone.isBlank() && email.isBlank()) {
                    Toast.makeText(context, "Preencha um Telefone ou E-mail!", Toast.LENGTH_SHORT).show()
                } else {
                    scope.launch {
                        dao.inserir(Contato(nome = nome, telefone = telefone, email = email))
                        Toast.makeText(context, "Cadastrado com Sucesso", Toast.LENGTH_SHORT).show()
                        nome = ""
                        telefone = ""
                        email = ""
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Destaque),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Salvar Contato", color = Color.White)
        }
    }
}
