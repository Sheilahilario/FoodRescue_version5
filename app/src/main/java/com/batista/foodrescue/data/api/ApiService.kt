package com.batista.foodrescue.data.api

import com.batista.foodrescue.data.model.Produto
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET("api/todo")
    suspend fun listProduto(): Response<List<Produto>>

    @POST("api/todo")
    suspend fun addProduto(
        @Body produto: Produto
    ): Response<Produto>

    @PUT("api/todo")
    suspend fun updateProduto(
        @Body produto: Produto
    ): Response<Produto>

    @DELETE("api/todo/{tarefa}")
    suspend fun deleteProduto(
        @Path("tarefa") valor: Int
    ): Response<Produto>
}