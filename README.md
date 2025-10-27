# 🎬 IMBd Filmes

Um aplicativo Android moderno construído em **Kotlin** e **Jetpack Compose** que simula um explorador de filmes, similar ao IMDb ou Netflix. O projeto foi desenvolvido como trabalho final da disciplina de Programação Mobile, focando no consumo de API, gerenciamento de estado (ViewModel) e construção de interfaces reativas.

## ✨ Funcionalidades Implementadas

O aplicativo atende a todos os requisitos do projeto e implementa desafios extras para uma experiência completa:

| Funcionalidade | Status | Descrição |
| :--- | :--- | :--- |
| **Busca de Filmes** | ✅ Completo | Permite buscar filmes e séries por título através de uma barra de pesquisa. |
| **Listagem Reativa** | ✅ Completo | Exibe os resultados da busca em uma lista com pôster, título e ano. |
| **Detalhes do Filme** | ✅ Completo | Ao clicar em um item da lista, navega para uma tela de detalhes completa, exibindo sinopse, elenco, direção, classificação IMDb e mais. |
| **Consumo de API** | ✅ Completo | Integração com a **OMDb API** (Open Movie Database) para busca e detalhes. |
| **Arquitetura** | ✅ Completo | Utilização de `ViewModel` para gerenciamento de estado e `Retrofit` + `Coroutines` para operações assíncronas. |
| **Interface Moderna** | ✅ Completo | Design limpo e intuitivo com **Jetpack Compose** e paleta de cores inspirada em cinema. |
| **Tratamento de Erros** | ✅ Completo | Exibe mensagens amigáveis em caso de erro de conexão ou busca sem resultados. |
| **Modo Escuro** | ✅ Completo | Suporte a temas claro e escuro, seguindo a preferência do sistema. |

## 🛠️ Tecnologias Utilizadas

*   **Linguagem:** Kotlin
*   **UI Toolkit:** Jetpack Compose
*   **Arquitetura:** MVVM (Model-View-ViewModel)
*   **Consumo de API:** Retrofit 2 e GSON Converter
*   **Assincronicidade:** Kotlin Coroutines
*   **Navegação:** Jetpack Navigation Compose
*   **Carregamento de Imagens:** Coil Compose

## 🔑 Configuração

O projeto utiliza a **OMDb API**. A chave de acesso (`API_KEY`) está configurada diretamente no arquivo `app/src/main/java/com/hollow/imbdfilmes/Api/Api.kt` como uma constante.

```kotlin
const val API_KEY = "5b0ca64d" // Chave fornecida para o projeto
const val BASE_URL = "https://www.omdbapi.com/"
```

## 🖼️ Telas do Aplicativo

*(Observação: As imagens das telas devem ser adicionadas após a compilação e execução do projeto no Android Studio.)*

### 1. Tela de Busca (SearchScreen)
A tela inicial permite ao usuário digitar o nome de um filme ou série. A busca é realizada automaticamente (debounce) após um breve período de digitação.

### 2. Tela de Detalhes (DetailScreen)
Exibe informações completas sobre o filme selecionado, incluindo pôster em alta resolução, sinopse, elenco, diretor, e a classificação IMDb.

## 🚀 Como Executar

1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/Gui0r/IMBdFilmes.git
    ```
2.  **Abra no Android Studio:**
    Abra a pasta `IMBdFilmes` no Android Studio (versão Hedgehog ou superior).
3.  **Sincronize o Gradle:**
    Aguarde a sincronização automática dos arquivos Gradle e a instalação das dependências.
4.  **Execute:**
    Selecione um emulador ou dispositivo físico com Android 8.0 (API 26) ou superior e clique em **Run** (▶️).

---
**Desenvolvido por:** [Seu Nome/Nome do Aluno]
**Data:** Outubro de 2025
