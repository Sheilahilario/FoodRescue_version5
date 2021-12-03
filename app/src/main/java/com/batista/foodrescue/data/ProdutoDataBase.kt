package com.batista.foodrescue.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.batista.foodrescue.data.model.Produto

@Database(entities = [Produto::class], version = 1, exportSchema = false)
abstract class ProdutoDataBase: RoomDatabase() {

    abstract fun produtoDao(): ProdutoDao

    companion object {
        @Volatile
        private var INSTANCE: ProdutoDataBase? = null

        fun getDatabase(context: Context): ProdutoDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProdutoDataBase::class.java,
                    "produtos_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}