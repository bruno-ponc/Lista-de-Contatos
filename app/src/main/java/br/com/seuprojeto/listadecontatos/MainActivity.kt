package br.com.seuprojeto.listadecontatos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // iniciar o banco e o DAO
        val database = AppDatabase.getDatabase(applicationContext)
        val contatoDao = database.contatoDao()

        setContent {
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = "tela_cadastro"
            ) {
                // rota TelaCadastro
                composable("tela_cadastro") {
                    TelaCadastro(
                        dao = contatoDao,
                        onIrParaContatos = { navController.navigate("tela_lista") }
                    )
                }

                // rota TelaMeusContatos
                composable("tela_lista") {
                    TelaMeusContatos(
                        dao = contatoDao,
                        navController = navController, 
                        onVoltarCadastro = { navController.navigate("tela_cadastro") }
                    )
                }

                // rota TelaEditarContato
                composable(
                    route = "editar/{contatoId}",
                    arguments = listOf(navArgument("contatoId") { type = NavType.IntType }) 
                ) { backStackEntry ->
                    val id = backStackEntry.arguments?.getInt("contatoId") ?: 0
                    TelaEditarContato(
                        contatoId = id,
                        navController = navController,
                        database = database
                    )
                }
            }
        }
    }
}
