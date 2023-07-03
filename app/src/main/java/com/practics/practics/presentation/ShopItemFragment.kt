package com.practics.practics.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.practics.practics.R
import com.practics.practics.databinding.FragmentShopItemBinding
import com.practics.practics.domain.ShopItem

class ShopItemFragment : Fragment() {

    private lateinit var binding: FragmentShopItemBinding
    private lateinit var vm: ShopItemViewModel

    private var shopItemId: Int = ShopItem.UNDEFINED_ID
    private var screenMode: String = MODE_UNKNOWN


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseParams()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShopItemBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm = ViewModelProvider(this)[ShopItemViewModel::class.java]
        lunchMode()
        textChangeListener()
        observeViewModel()
    }


    private fun observeViewModel() {
        vm.errorInputName.observe(viewLifecycleOwner) {
            val massage = if (it) {
                resources.getString(R.string.incorrect_name)
            } else {
                null
            }
            binding.tilName.error = massage
        }
        vm.errorInputCount.observe(viewLifecycleOwner) {
            val massage = if (it) {
                resources.getString(R.string.incorrect_count)
            } else {
                null
            }
            binding.tilCount.error = massage
        }

        vm.shouldCloseScreen.observe(viewLifecycleOwner) {
            activity?.onBackPressed()
        }
    }

    private fun lunchMode() {
        when (screenMode) {
            EXTRA_MODE_VALUE_EDIT -> lunchEditMode()
            EXTRA_MODE_VALUE_ADD -> lunchAddMode()
        }
    }

    private fun lunchEditMode() {
        binding.btnSave.text = resources.getString(R.string.edit)
        vm.getShopItem(shopItemId)
        vm.shopItem.observe(viewLifecycleOwner) {
            binding.edtName.setText(it.name)
            binding.edtCount.setText(it.count.toString())
        }
        binding.btnSave.setOnClickListener {
            vm.editShopItem(binding.edtName.text?.toString(), binding.edtCount.text?.toString())
        }
    }

    private fun lunchAddMode() {
        binding.btnSave.setOnClickListener {
            vm.addShopItem(binding.edtName.text?.toString(), binding.edtCount.text?.toString())
        }
    }


    private fun textChangeListener() {
        binding.edtName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                vm.resetErrorName()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        binding.edtCount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                vm.resetErrorCount()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    private fun parseParams() {
        val args = requireArguments()
        if (!args.containsKey(EXTRA_MODE)) {
            throw RuntimeException("Param screen mode undefined")
        }
        val intentMode = args.getString(EXTRA_MODE)
        if (intentMode != EXTRA_MODE_VALUE_EDIT &&
            intentMode != EXTRA_MODE_VALUE_ADD
        ){
            throw RuntimeException("Param screen mode undefined $intentMode")
        }
        screenMode = intentMode
        if (intentMode == EXTRA_MODE_VALUE_EDIT) {
            if (!args.containsKey(EXTRA_SHOP_ITEM_ID)) {
                throw java.lang.RuntimeException("undefined item id ")
            }
            shopItemId = args.getInt(EXTRA_SHOP_ITEM_ID, ShopItem.UNDEFINED_ID )
        }
    }

    companion object {
        private const val EXTRA_MODE = "extra_mode"
        private const val EXTRA_MODE_VALUE_ADD = "extra_value_add"
        private const val EXTRA_MODE_VALUE_EDIT = "extra_value_edit"
        private const val EXTRA_SHOP_ITEM_ID = "extra_shop_item_id"
        private const val MODE_UNKNOWN = ""

        fun newInstanceAddItem() = ShopItemFragment().apply {
            arguments = Bundle().apply {
                putString(EXTRA_MODE,EXTRA_MODE_VALUE_ADD)
            }
        }

        fun newInstanceEditItem(shopItemId: Int) = ShopItemFragment().apply {
            arguments = Bundle().apply {
                putString(EXTRA_MODE, EXTRA_MODE_VALUE_EDIT)
                putInt(EXTRA_SHOP_ITEM_ID,shopItemId)
            }
        }
    }
}