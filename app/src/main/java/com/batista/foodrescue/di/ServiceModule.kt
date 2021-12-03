package com.batista.foodrescue.di

import android.content.Context
import com.batista.foodrescue.data.ProdutoDao
import com.batista.foodrescue.data.ProdutoDataBase
import com.batista.foodrescue.data.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideDao(@ApplicationContext context: Context): ProdutoDao{
        return ProdutoDataBase.getDatabase(context).produtoDao()
    }

    @Provides
    @Singleton
    fun provideTaskService(produtoDao: ProdutoDao): Repository {
        return Repository(produtoDao)
    }

}