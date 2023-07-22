package com.practics.practics.domain

import androidx.lifecycle.LiveData

interface ShopListRepository {

    suspend fun addShopItem(shopItem: ShopItem)

    suspend fun deleteShopItem(shopItem: ShopItem)

    suspend fun editShopItem(shopItem: ShopItem)

    suspend fun getShopItemById(shopItemId: Int):ShopItem

    fun getShopList(): LiveData<List<ShopItem>>
}