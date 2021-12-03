package com.batista.foodrescue.views.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.batista.foodrescue.R
import com.batista.foodrescue.data.model.Produto
import com.batista.foodrescue.views.OrderViewModel

class AdapterProduct(
    private val taskItemClickListener: ProdutoClickListener,
    private val orderViewModel: OrderViewModel
)
    : RecyclerView.Adapter<AdapterProduct.ProdutoViewHolder>() {

    private var listProduct = emptyList<Produto>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutoViewHolder {
        val layoutAdapter= LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_cardview, parent, false)

        return ProdutoViewHolder(layoutAdapter)
    }

    class ProdutoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textNome = view.findViewById<TextView>(R.id.textNomeProduto)
        val textStatus = view.findViewById<TextView>(R.id.textStatus)
        val textData = view.findViewById<TextView>(R.id.textData)
        val buttonEdit = view.findViewById<ImageView>(R.id.buttonEdit)
        val buttonExcluir = view.findViewById<ImageView>(R.id.buttonExcluir)
    }

    override fun onBindViewHolder(holder: ProdutoViewHolder, position: Int) {

        val produto = listProduct[position]

        holder.textNome.text = produto.name
        holder.textStatus.text = produto.status
        holder.textData.text = produto.dueDate
        holder.textStatus.text = produto.status

        holder.buttonExcluir.setOnClickListener {
            orderViewModel.deleteProduto(produto)
        }

        holder.itemView.setOnClickListener {
            taskItemClickListener.onTaskClicked(produto)
        }

    }

    override fun getItemCount(): Int {
        return listProduct.size
    }

    fun setData(produto: List<Produto>) {
        this.listProduct = produto
        notifyDataSetChanged()
    }
}

