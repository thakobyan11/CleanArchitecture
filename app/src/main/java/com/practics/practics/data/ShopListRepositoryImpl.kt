package com.practics.practics.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.practics.practics.domain.ShopItem
import com.practics.practics.domain.ShopListRepository
import kotlin.random.Random

class ShopListRepositoryImpl (
    application: Application
        ) : ShopListRepository {

    val daoShopItem = ShopDatabase.getInstance(application).getShopListDao()
    val mapper = ShopListMapper()

    override fun addShopItem(shopItem: ShopItem) {
        daoShopItem.addShopItem(mapper.mapEntityToDbModel(shopItem))
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        daoShopItem.deleteShopItem(shopItem.id)
    }


    override fun editShopItem(shopItem: ShopItem) {
        daoShopItem.addShopItem(mapper.mapEntityToDbModel(shopItem))
    }

    override fun getShopItemById(shopItemId: Int): ShopItem {
        return mapper.mapDbModelToEntity(daoShopItem.getShopItemById(shopItemId))
    }


    override fun getShopList() : LiveData<List<ShopItem>> = daoShopItem.getShopList()

}