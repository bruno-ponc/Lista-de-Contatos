# Lista de Contatos

Um aplicativo Android nativo e moderno para gerenciar sua lista de contatos local. Com ele, você pode cadastrar, visualizar, editar e excluir contatos com atualizações em tempo real na tela.

---

## Funcionalidades

* **Cadastro Completo:** Salva Nome, Telefone e E-mail.
* **Validação de Telefone:** O campo de telefone aceita **apenas números** e abre o teclado numérico automaticamente.
* **Lista Dinâmica:** Mostra os contatos em ordem alfabética. Se a lista estiver vazia, exibe um aviso.
* **Editar Contatos:** Abre uma tela de edição com os dados antigos já preenchidos para você alterar o que quiser.
* **Excluir Contatos:** Botão de lixeira que apaga o contato do banco de dados instantaneamente.

---

## Tecnologias Utilizadas

* **Linguagem:** Kotlin
* **Interface:** Jetpack Compose (Material Design 3)
* **Banco de Dados:** Room Database (Salva os dados direto no celular)
* **Navegação:** Compose Navigation (Para navegar entre as 3 telas)
* **Assincronismo:** Coroutines & Flow (Para o app não travar ao salvar os dados)

---

## Requisitos de Compilação

Se for rodar o projeto no seu computador, certifique-se de configurar o Android Studio para usar o **Java 17**, necessário para o funcionamento do banco de dados (Room/KAPT).

* Mude em: `File ➡️ Settings ➡️ Build, Execution, Deployment ➡️ Build Tools ➡️ Gradle` ➡️ **Gradle JDK** para **Java 17**.

---

## Como Rodar o App

1. Faça o clone deste repositório.
2. Abra o projeto no **Android Studio**.
3. Aguarde o Gradle sincronizar.
4. Clique no botão **Play** (Triângulo verde) para rodar no seu emulador ou celular.

---

## Telas

<img width="494" height="877" alt="Captura de tela 2026-05-25 174050" src="https://github.com/user-attachments/assets/58e0c5ea-f946-4bed-af6c-726dfcdf1b92" />
<img width="491" height="878" alt="Captura de tela 2026-05-25 174200" src="https://github.com/user-attachments/assets/b9295e21-ef4d-4fe0-a207-fdcbb9fd12cf" />
<img width="491" height="877" alt="Captura de tela 2026-05-25 174223" src="https://github.com/user-attachments/assets/d8eecff6-26bd-4c90-91ed-f4c10f06bb64" />

