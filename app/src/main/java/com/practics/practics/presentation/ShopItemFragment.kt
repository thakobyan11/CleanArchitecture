package com.practics.practics.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.practics.practics.databinding.FragmentShopItemBinding

class ShopItemFragment : Fragment() {

    private lateinit var binding : FragmentShopItemBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShopItemBinding.inflate(layoutInflater)
        return binding.root
    }
}