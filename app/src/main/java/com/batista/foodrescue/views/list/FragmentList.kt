package com.batista.foodrescue.views.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.batista.foodrescue.R
import com.batista.foodrescue.views.list.adapter.AdapterProduct
import com.batista.foodrescue.views.list.adapter.ProdutoClickListener
import com.batista.foodrescue.databinding.FragmentProdutosBinding
import com.batista.foodrescue.data.model.Produto
import com.batista.foodrescue.views.OrderViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FragmentList : Fragment(), ProdutoClickListener {

    private val viewModel: OrderViewModel by activityViewModels()

    private var _binding: FragmentProdutosBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProdutosBinding.inflate(inflater, container, false)

        val adapter = AdapterProduct(this, viewModel)
        binding.fragmentProdutos.layoutManager = LinearLayoutManager(context)
        binding.fragmentProdutos.adapter = adapter
        binding.fragmentProdutos.setHasFixedSize(true)

        viewModel.listProduct()
        lifecycleScope.launch {
            viewModel.myQueryResponse.collect {
                    response -> adapter.setData(response)
            }
        }
        /*

        viewModel.myDeleteResponse.observe(viewLifecycleOwner, {
            viewModel.listProduct()
            Toast.makeText(
                context, "Produto deletado!",
                Toast.LENGTH_LONG
            ).show()
        })


         */

        binding.floatingActionButton.setOnClickListener {
            viewModel.productSelected = null
            findNavController().navigate(R.id.action_fragmentTelaListaProdutos_to_fragmentTelaForm)
        }
        return binding.root
    }

    override fun onTaskClicked(produto: Produto) {
        viewModel.productSelected = produto
        findNavController().navigate(R.id.action_fragmentTelaListaProdutos_to_fragmentTelaForm)
    }

}