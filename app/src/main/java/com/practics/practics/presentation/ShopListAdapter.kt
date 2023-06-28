package com.practics.practics.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.practics.practics.R
import com.practics.practics.domain.ShopItem

class ShopListAdapter : ListAdapter<ShopItem,ShopItemViewHolder>(ShopItemDiffCallBack()) {

    var shopItemLongClickListener: ((ShopItem) -> Unit)? = null
    var shopItemClickListener: ((ShopItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        return if (viewType == VIEW_TYPE_IS_ACTIVE) {
            ShopItemViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.shop_list_item, parent, false)
            )
        } else {
            ShopItemViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.shop_list_item_light, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = getItem(position)
        holder.name.text = "${shopItem.name} "
        holder.count.text = getItem(position).count.toString()
        holder.view.setOnLongClickListener {
            shopItemLongClickListener?.invoke(shopItem)
            true
        }
        holder.view.setOnClickListener {
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