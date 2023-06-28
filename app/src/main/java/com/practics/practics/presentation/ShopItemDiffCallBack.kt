package com.practics.practics.presentation

import androidx.recyclerview.widget.DiffUtil
import com.practics.practics.domain.ShopItem

class ShopItemDiffCallBack : DiffUtil.ItemCallback<ShopItem>() {
    override fun areItemsTheSame(oldItem: ShopItem, newItem: ShopItem) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: ShopItem, newItem: ShopItem) = oldItem == newItem
}