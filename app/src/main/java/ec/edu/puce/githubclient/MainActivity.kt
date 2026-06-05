package ec.edu.puce.githubclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import ec.edu.puce.githubclient.ui.screens.RepoForm
import ec.edu.puce.githubclient.ui.screens.RepoList
import ec.edu.puce.githubclient.ui.theme.GithubClientTheme
import ec.edu.puce.githubclient.viewmodels.RepoListViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GithubClientTheme {
                // Instanciamos el ViewModel una vez para compartirlo
                val sharedViewModel: RepoListViewModel = viewModel()
                // Controlamos qué pantalla se ve
                var currentScreen by remember { mutableStateOf("List") }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    if (currentScreen == "List") {
                        RepoList(
                            modifier = Modifier.padding(innerPadding),
                            viewModel = sharedViewModel,
                            onAddClick = { currentScreen = "Form" } // Viaja al formulario
                        )
                    } else {
                        RepoForm(
                            modifier = Modifier.padding(innerPadding),
                            viewModel = sharedViewModel,
                            onBackClick = { currentScreen = "List" } // Regresa a la lista
                        )
                    }
                }
            }
        }
    }
}