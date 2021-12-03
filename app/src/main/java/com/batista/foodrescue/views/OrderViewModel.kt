package com.batista.foodrescue.views

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.batista.foodrescue.data.model.Produto
import com.batista.foodrescue.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val selectedDateLiveData: MutableLiveData<String> = MutableLiveData()

    var productSelected: Produto? = null

    lateinit var myQueryResponse: Flow<List<Produto>>

    init {
        val formatter = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
        val date = formatter.format(Date())
        selectedDateLiveData.postValue(date.toString())

        viewModelScope.launch {
            myQueryResponse = repository.queryAllProducts()
        }
    }

    fun listProduct() {
        viewModelScope.launch {
            try {
                val response = repository.listProduct()
                if (response.isSuccessful) {
                    val listProduct = response.body()!!
                    for (product in listProduct) {
                        val findProduct = repository.queryById(product.id)
                        if (findProduct.first() != null) {
                            repository.updateRoom(product)
                        } else {
                            repository.insertProduct(product)
                        }
                    }
                } else {
                    Log.d("msgErro", "Erro: ${response.errorBody().toString()}")
                }
            } catch (e: Exception) {
                Log.d("msg", e.message.toString())
            }
        }
    }

    fun addProduto(produto: Produto) {
        viewModelScope.launch {
            try {
                val response = repository.addProduto(produto)
                if (response.isSuccessful) {
                    repository.insertProduct(response.body()!!)
                } else {
                    repository.insertProduct(produto)
                }
            } catch (e: Exception) {
                repository.insertProduct(produto)
            }
        }
    }

    fun updateProduto(produto: Produto) {
        viewModelScope.launch {
            try {
                repository.updateProduto(produto)
                repository.updateRoom(produto)
            } catch (e: Exception) {
                repository.updateRoom(produto)
            }
        }
    }

    fun deleteProduto(produto: Produto) {
        viewModelScope.launch {
            try {
                repository.deleteProduto(produto.id)
                repository.deleteProdutoRoom(produto)
            } catch (e: Exception) {
                repository.deleteProdutoRoom(produto)
            }
        }
    }
}

