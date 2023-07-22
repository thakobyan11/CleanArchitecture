package com.practics.practics.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.practics.practics.domain.ShopItem
import com.practics.practics.domain.ShopListRepository

class ShopListRepositoryImpl(
    application: Application
) : ShopListRepository {

    private val daoShopItem = ShopDatabase.getInstance(application).getShopListDao()
    private val mapper = ShopListMapper()

    override suspend fun addShopItem(shopItem: ShopItem) {
        daoShopItem.addShopItem(mapper.mapEntityToDbModel(shopItem))
    }

    override suspend fun deleteShopItem(shopItem: ShopItem) {
        daoShopItem.deleteShopItem(shopItem.id)
    }


    override suspend fun editShopItem(shopItem: ShopItem) {
        daoShopItem.addShopItem(mapper.mapEntityToDbModel(shopItem))
    }

    override suspend fun getShopItemById(shopItemId: Int): ShopItem {
        return mapper.mapDbModelToEntity(daoShopItem.getShopItemById(shopItemId))
    }


    override fun getShopList(): LiveData<List<ShopItem>> = Transformations.map(
        daoShopItem.getShopList()
    ) {
        mapper.mapListDbModelToListEntity(it)
    }
}