package com.example.itemlist.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.itemlist.databinding.FragmentProductDetailBinding

class ProductDetailFragment : Fragment() {

    private lateinit var binding: FragmentProductDetailBinding

    private val args: ProductDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentProductDetailBinding.inflate(inflater, container, false)

        binding.tvName.text = args.product.title
        binding.tvPrice.text = "$${args.product.price}"
        binding.tvDescription.text = args.product.description

        Glide.with(requireContext())
            .load(args.product.image)
            .into(binding.ivProduct)

        binding.btnAddToCart.setOnClickListener {

            Toast.makeText(
                requireContext(),
                "Added to Cart",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

        return binding.root
    }
}