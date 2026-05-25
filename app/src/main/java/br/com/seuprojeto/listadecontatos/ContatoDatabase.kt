package br.com.seuprojeto.listadecontatos

import android.content.Context
import androidx.room.*
import kotlinx.coroutines.flow.Flow

// tabela do banco
@Entity(tableName = "contatos")
data class Contato(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nome: String,
    val telefone: String,
    val email: String
)

// comandos sql
@Dao
interface ContatoDao {
    @Insert
    suspend fun inserir(contato: Contato)

    @Query("SELECT * FROM contatos ORDER BY nome ASC")
    fun listarTodos(): Flow<List<Contato>>

    @Delete
    suspend fun excluir(contato: Contato)

    @Update
    suspend fun atualizar(contato: Contato)

    @Query("SELECT * FROM contatos WHERE id = :id LIMIT 1")
    suspend fun buscarPorId(id: Int): Contato?
}

// classe do banco
@Database(entities = [Contato::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun contatoDao(): ContatoDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "contatos_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}