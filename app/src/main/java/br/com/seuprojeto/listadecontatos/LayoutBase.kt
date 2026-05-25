package br.com.seuprojeto.listadecontatos

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.seuprojeto.listadecontatos.ui.theme.*

@Composable
fun LayoutPadrao(
    subtitulo: String,
    botaoTexto: String,
    onBotaoClique: () -> Unit,
    conteudo: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Fundo)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Lista de Contatos",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Destaque,
            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
        )

        Text(
            text = subtitulo,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            color = Secundaria,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            conteudo()
        }

        Button(
            onClick = { onBotaoClique() },
            colors = ButtonDefaults.buttonColors(containerColor = Base, contentColor = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = botaoTexto, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
    }
}