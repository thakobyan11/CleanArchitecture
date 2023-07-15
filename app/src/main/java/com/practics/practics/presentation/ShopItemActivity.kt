package com.practics.practics.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.practics.practics.R
import com.practics.practics.databinding.ActivityShopItemBinding
import com.practics.practics.domain.ShopItem

class ShopItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShopItemBinding

    private var shopItemId:Int = ShopItem.UNDEFINED_ID
    private var screenMode:String = MODE_UNKNOWN


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        parseIntent()
        lunchMode()
    }

    private fun lunchMode(){
        val fragment = when (screenMode) {
            EXTRA_MODE_VALUE_EDIT -> ShopItemFragment.newInstanceEditItem(shopItemId)
            EXTRA_MODE_VALUE_ADD -> ShopItemFragment.newInstanceAddItem()
            else -> throw java.lang.RuntimeException("Unknown screen mode $screenMode")
        }
        supportFragmentManager.beginTransaction()
            .add(R.id.frag_cont_view,fragment)
            .commit()
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
    }

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
