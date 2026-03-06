package com.example.itemlist

import Product
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.itemlist.databinding.FragmentProductListBinding
class ProductListFragment : Fragment() {

    private var _binding: FragmentProductListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val productList = listOf(
            Product(1, "Shoes", "₹999", "Comfortable running shoes"),
            Product(2, "Watch", "₹1999", "Stylish wrist watch"),
            Product(3, "Bag", "₹1499", "Premium travel bag"),
            Product(4, "Headphones", "₹1299", "Noise cancelling"),
            Product(5, "Phone", "₹14999", "Latest smartphone")
        )

        val adapter = ProductAdapter(productList) { product ->

            val action =
                ProductListFragmentDirections
                    .actionProductListFragmentToDetailFragment(product.id)

            findNavController().navigate(action)
        }

        binding.recyclerView.layoutManager =
            LinearLayoutManager(requireContext())

        binding.recyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}