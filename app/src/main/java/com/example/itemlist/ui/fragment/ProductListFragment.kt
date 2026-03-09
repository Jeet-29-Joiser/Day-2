package com.example.itemlist.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.navigation.fragment.findNavController
import com.example.itemlist.ProductListFragmentDirections
import com.example.itemlist.databinding.FragmentProductListBinding
import com.example.itemlist.ui.adapter.ProductAdapter
import com.example.itemlist.viewmodel.ProductViewModel

class ProductListFragment : Fragment() {

    private var _binding: FragmentProductListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProductViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProductListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.loadProducts()

        viewModel.products.observe(viewLifecycleOwner) { products ->

            val adapter = ProductAdapter(products) { product ->

                val action =
                    ProductListFragmentDirections
                        .actionProductListFragmentToDetailFragment(product.id)

                findNavController().navigate(action)
            }

            binding.recyclerView.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}