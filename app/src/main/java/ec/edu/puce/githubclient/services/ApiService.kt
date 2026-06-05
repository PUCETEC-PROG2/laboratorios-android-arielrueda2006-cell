package ec.edu.puce.githubclient.services

import ec.edu.puce.githubclient.models.Repository
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

data class RepoRequest(
    val name: String,
    val description: String
)

interface ApiService {
    @GET("/user/repos")
    suspend fun getRepositories(
        @Query("sort") sort: String = "created",
        @Query("direction") direction: String = "desc",
        @Query("affiliation") affiliation: String = "owner,collaborator,organization_member",
        @Query("per_page") perPage: Int = 100,
        @Query("t") t: String = System.currentTimeMillis().toString()
    ): List<Repository>

    @POST("/user/repos")
    suspend fun createRepository(
        @Body request: RepoRequest
    ): Repository
}