package com.practics.practics.data

import com.practics.practics.domain.ShopItem

class ShopListMapper {

    fun mapEntityToDbModel(shopItem: ShopItem) = ShopItemDB(
        id = shopItem.id,
        name = shopItem.name,
        count = shopItem.count,
        isActive = shopItem.isActive
    )

    fun mapDbModelToEntity(shopItemDB: ShopItemDB) = ShopItem(
        id = shopItemDB.id,
        name = shopItemDB.name,
        count = shopItemDB.count,
        isActive = shopItemDB.isActive
    )

    fun mapListDbModelToListEntity(list:List<ShopItemDB>) = list.map {
        mapDbModelToEntity(it)
    }
}