package com.batista.foodrescue.data.repository

import com.batista.foodrescue.data.ProdutoDao
import com.batista.foodrescue.data.api.RetrofitInstance
import com.batista.foodrescue.data.model.Produto
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class Repository (private val produtoDao: ProdutoDao) {


    suspend fun listProduct(): Response<List<Produto>> {
        return RetrofitInstance.api.listProduto()
    }

    suspend fun addProduto(produto: Produto): Response<Produto> {
        return RetrofitInstance.api.addProduto(produto)
    }

    suspend fun updateProduto(produto: Produto): Response<Produto> {
        return RetrofitInstance.api.updateProduto(produto)
    }

    suspend fun deleteProduto(valor: Int): Response<Produto> {
        return RetrofitInstance.api.deleteProduto(valor)
    }

    // Room

    fun queryAllProducts(): Flow<List<Produto>> {
        return produtoDao.queryAllProduto()
    }

    suspend fun insertProduct(produto: Produto){
        produtoDao.insertProduto(produto)
    }

    fun queryById(id: Int): Flow<Produto?> {
        return produtoDao.queryById(id)
    }

     suspend fun updateRoom(produto: Produto){
        produtoDao.updateRoom(produto)
    }

    suspend fun deleteProdutoRoom(produto: Produto){
        return produtoDao.deleteProdutoRoom(produto)
    }
}