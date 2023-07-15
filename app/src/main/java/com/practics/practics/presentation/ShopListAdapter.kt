package com.practics.practics.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.practics.practics.R
import com.practics.practics.databinding.ShopListItemBinding
import com.practics.practics.databinding.ShopListItemLightBinding
import com.practics.practics.domain.ShopItem
import java.lang.RuntimeException

class ShopListAdapter : ListAdapter<ShopItem,ShopItemViewHolder>(ShopItemDiffCallBack()) {

    var shopItemLongClickListener: ((ShopItem) -> Unit)? = null
    var shopItemClickListener: ((ShopItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val layout = when(viewType) {
            VIEW_TYPE_IS_ACTIVE -> R.layout.shop_list_item
            VIEW_TYPE_IS_NOT_ACTIVE -> R.layout.shop_list_item_light
            else -> { throw RuntimeException("invalid layout")}
        }
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
                    LayoutInflater.from(parent.context), layout, parent, false
        )
        return ShopItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = getItem(position)
        val binding = holder.binding
        when(binding){
            is ShopListItemBinding ->{binding.shopItem = shopItem}
            is ShopListItemLightBinding ->{binding.shopItem = shopItem}
        }
        binding.root.setOnLongClickListener {
            shopItemLongClickListener?.invoke(shopItem)
            true
        }
        binding.root.setOnClickListener {
            shopItemClickListener?.invoke(shopItem)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).isActive) {
            VIEW_TYPE_IS_ACTIVE
        } else {
            VIEW_TYPE_IS_NOT_ACTIVE
        }
    }

    companion object {
        const val VIEW_TYPE_IS_ACTIVE = 1
        const val VIEW_TYPE_IS_NOT_ACTIVE = 2
        const val MAX_POOL_COUNT = 15
    }
}
