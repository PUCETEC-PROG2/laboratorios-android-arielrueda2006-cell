package ec.edu.puce.githubclient.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable

@Composable
fun RepoList() {
    Column {
        RepoItem(
            name = "Repositorio de Android",
            description = "Repositorio creado en Kotlin",
            avatarUrl = "skdsjklas",
            language = "Kotlin"
        )
        RepoItem(
            name = "Repositorio de IoS",
            description = "Repositorio creado en Kotlin",
            avatarUrl = "skdsjklas",
            language = "Java"
        )
        RepoItem(
            name = "Repositorio de jv",
            description = "Repositorio creado en Kotlin",
            avatarUrl = "skdsjklas",
            language = "C++"
        )
        RepoItem(
            name = "Repositorio de Android",
            description = "Repositorio creado en Kotlin",
            avatarUrl = "skdsjklas",
            language = "Java"
        )
        RepoItem(
            name = "Repositorio de Kt",
            description = "Repositorio creado en Kotlin",
            avatarUrl = "skdsjklas",
            language = "Kotlin"
        )
    }
}
