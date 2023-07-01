package com.practics.practics.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.practics.practics.R
import com.practics.practics.databinding.ActivityMainBinding
import com.practics.practics.databinding.ActivityShopItemBinding
import com.practics.practics.domain.ShopItem

class ShopItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShopItemBinding
    private lateinit var vm: ShopItemViewModel
    private var shopItemId = ShopItem.UNDEFINED_ID
    private var screenMode = MODE_UNKNOWN

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //parseIntent()
        vm = ViewModelProvider(this)[ShopItemViewModel::class.java]
        /*lunchMode()
        textChangeListener()
        observeViewModel()*/
    }

    /*private fun observeViewModel(){
        vm.errorInputName.observe(this){
            val massage = if (it){
                resources.getString(R.string.incorrect_name)
            }else{
                null
            }
            binding.tilName.error = massage
        }
        vm.errorInputCount.observe(this){
            val massage = if (it){
                resources.getString(R.string.incorrect_count)
            }else{
                null
            }
            binding.tilCount.error = massage
        }

        vm.shouldCloseScreen.observe(this){
            finish()
        }
    }

    private fun lunchMode(){
        when (screenMode) {
            EXTRA_MODE_VALUE_EDIT -> lunchEditMode()
            EXTRA_MODE_VALUE_ADD -> lunchAddMode()
        }
    }

    private fun lunchEditMode() {
        binding.btnSave.text = resources.getString(R.string.edit)
        vm.getShopItem(shopItemId)
        vm.shopItem.observe(this){
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


    private fun textChangeListener(){
        binding.edtName.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                vm.resetErrorName()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        binding.edtCount.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                vm.resetErrorCount()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    private fun parseIntent() {
        if (!intent.hasExtra(EXTRA_MODE)) {
            throw RuntimeException("Param screen mode undefined")
        }
        val intentMode = intent.getStringExtra(EXTRA_MODE)
        if (intentMode != EXTRA_MODE_VALUE_EDIT &&
            intentMode != EXTRA_MODE_VALUE_ADD){
            throw RuntimeException("Param screen mode undefined $intentMode")
        }
        screenMode = intentMode
        if (intentMode == EXTRA_MODE_VALUE_EDIT) {
            if (!intent.hasExtra(EXTRA_SHOP_ITEM_ID)) {
                throw java.lang.RuntimeException("undefined item id ")
            }
            shopItemId = intent.getIntExtra(EXTRA_SHOP_ITEM_ID, ShopItem.UNDEFINED_ID )
        }
    }*/

    companion object {
        private const val EXTRA_MODE = "extra_mode"
        private const val EXTRA_MODE_VALUE_ADD = "extra_value_add"
        private const val EXTRA_MODE_VALUE_EDIT = "extra_value_edit"
        private const val EXTRA_SHOP_ITEM_ID = "extra_shop_item_id"
        private const val MODE_UNKNOWN = ""

        fun newIntentForEdit(context: Context, shopItemId: Int): Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_MODE, EXTRA_MODE_VALUE_EDIT)
            intent.putExtra(EXTRA_SHOP_ITEM_ID, shopItemId)
            return intent
        }

        fun newIntentForAdd(context: Context): Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_MODE, EXTRA_MODE_VALUE_ADD)
            return intent
        }
    }
}
