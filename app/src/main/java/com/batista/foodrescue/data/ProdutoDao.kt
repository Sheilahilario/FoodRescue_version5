package com.batista.foodrescue.data

import androidx.room.*
import com.batista.foodrescue.data.model.Produto
import kotlinx.coroutines.flow.Flow

@Dao
interface ProdutoDao{

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProduto(produto: Produto)

    @Query("SELECT * FROM produtos_table ORDER BY id ASC")
    fun queryAllProduto(): Flow<List<Produto>>

    @Query("SELECT * FROM produtos_table WHERE id = :id")
    fun queryById(id: Int): Flow<Produto?>

    @Update
    suspend fun updateRoom(produto: Produto)

    @Delete
    suspend fun deleteProdutoRoom(produto: Produto)

}