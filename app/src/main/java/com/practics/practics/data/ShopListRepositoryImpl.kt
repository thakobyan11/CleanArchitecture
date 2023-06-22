package com.practics.practics.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.practics.practics.domain.ShopItem
import com.practics.practics.domain.ShopListRepository

class ShopListRepositoryImpl : ShopListRepository {

    private val shopListLD = MutableLiveData<List<ShopItem>>()
    private val shopList = mutableListOf<ShopItem>()

    private var autoIncrementId = 0

    init {
        for (i in 0..30){
            addShopItem(ShopItem(name = "item $i", count = i, isActive = true))
        }
    }

    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == -1) {
            shopItem.id = autoIncrementId++
        }
        shopList.add(shopItem)
        updateList()
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        updateList()
    }

    // will be changed
    override fun editShopItem(shopItem: ShopItem) {
        deleteShopItem(getShopItemById(shopItem.id))
        addShopItem(shopItem)
    }

    override fun getShopItemById(shopItemId: Int): ShopItem {
        return shopList.find { it.id == shopItemId }
            ?: throw RuntimeException("Element with id $shopItemId not found")
    }

    override fun getShopList(): LiveData<List<ShopItem>> = shopListLD

    private fun updateList(){
        shopListLD.value = shopList.toList()
    }
}