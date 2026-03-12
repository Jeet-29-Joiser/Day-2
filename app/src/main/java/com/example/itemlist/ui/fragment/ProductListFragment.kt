package com.example.itemlist.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.itemlist.databinding.FragmentProductListBinding
import com.example.itemlist.ui.adapter.ProductAdapter
import com.example.itemlist.viewmodel.ProductUiState
import com.example.itemlist.viewmodel.ProductViewModel
import androidx.navigation.fragment.navArgs

class ProductListFragment : Fragment() {

    private val args: ProductListFragmentArgs by navArgs()

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

        binding.recyclerView.layoutManager =
            LinearLayoutManager(requireContext())

        viewModel.loadProducts(args.categoryName)

        viewModel.uiState.observe(viewLifecycleOwner) { state ->

            when (state) {

                is ProductUiState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is ProductUiState.Success -> {

                    binding.progressBar.visibility = View.GONE

                    val adapter =
                        ProductAdapter(state.products){ product ->

                            val action =
                                ProductListFragmentDirections
                                    .actionProductListFragmentToProductDetailFragment(product)

                            findNavController().navigate(action)
                        }

                    binding.recyclerView.adapter = adapter
                }

                is ProductUiState.Error -> {

                    binding.progressBar.visibility = View.GONE

                    Toast.makeText(
                        requireContext(),
                        state.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}