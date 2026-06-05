package ec.edu.puce.githubclient.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ec.edu.puce.githubclient.models.Repository
import ec.edu.puce.githubclient.services.RepoRequest
import ec.edu.puce.githubclient.services.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RepoListViewModel : ViewModel() {
    private val _repos = MutableStateFlow<List<Repository>>(emptyList())
    val repos: StateFlow<List<Repository>> = _repos.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMsg = MutableStateFlow<String?>(null)
    val errorMsg: StateFlow<String?> = _errorMsg.asStateFlow()

    private val _toastMessage = MutableStateFlow<String?>(null)
    val toastMessage: StateFlow<String?> = _toastMessage.asStateFlow()

    init {
        fetchRepos()
    }

    fun fetchRepos() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMsg.value = null
            try {
                _repos.value = RetrofitClient.apiService.getRepositories()
            } catch (e: Exception) {
                _errorMsg.value = "Error al cargar repositorios: ${e.localizedMessage}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun createRepo(name: String, description: String, onSuccess: () -> Unit) {
        if (name.isBlank()) {
            _toastMessage.value = "El nombre del repositorio es obligatorio"
            return
        }

        viewModelScope.launch {
            _isLoading.value = true
            try {
                val request = RepoRequest(name, description)
                RetrofitClient.apiService.createRepository(request)
                _toastMessage.value = "Repositorio '$name' creado "
                fetchRepos()
                onSuccess()
            } catch (e: Exception) {
                _toastMessage.value = "Error al crear: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun clearToastMessage() {
        _toastMessage.value = null
    }
}