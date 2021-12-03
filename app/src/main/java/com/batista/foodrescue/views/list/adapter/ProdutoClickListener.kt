package com.batista.foodrescue.views.list.adapter

import com.batista.foodrescue.data.model.Produto

interface ProdutoClickListener {

    fun onTaskClicked(produto: Produto)
}