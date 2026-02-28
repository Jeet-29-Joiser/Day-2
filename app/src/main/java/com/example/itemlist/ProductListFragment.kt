package com.example.itemlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
        super.onViewCreated(view, savedInstanceState)

        val productList = listOf(
            Product("Shoes", "₹999", "Comfortable running shoes"),
            Product("Watch", "₹1999", "Stylish wrist watch"),
            Product("Bag", "₹1499", "Premium travel bag"),
            Product("Headphones", "₹1299", "Noise cancelling"),
            Product("Phone", "₹14999", "Latest smartphone")
        )

        val adapter = ProductAdapter(productList) { product ->

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, DetailFragment())
                .addToBackStack(null)
                .commit()
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}