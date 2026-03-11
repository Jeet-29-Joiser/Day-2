package com.example.itemlist.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.itemlist.R
import com.example.itemlist.data.Category
import com.example.itemlist.databinding.FragmentCategoryBinding
import com.example.itemlist.ui.adapter.CategoryAdapter

class CategoryFragment : Fragment() {

    private lateinit var binding: FragmentCategoryBinding

    private val categoryList = listOf(

        Category("electronics", R.drawable.electronics),
        Category("jewelery", R.drawable.jewelery),
        Category("men's clothing", R.drawable.men_clothing),
        Category("women's clothing", R.drawable.women_clothing)

    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentCategoryBinding.inflate(inflater, container, false)

        binding.recyclerView.layoutManager =
            LinearLayoutManager(requireContext())

        binding.recyclerView.adapter =
            CategoryAdapter(categoryList) { category ->

                val action =
                    CategoryFragmentDirections
                        .actionCategoryFragmentToProductListFragment(category.name)

                findNavController().navigate(action)
            }

        return binding.root
    }
}