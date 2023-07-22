package com.practics.practics.domain

class GetShopItemUseCase(private val shopListRepository: ShopListRepository) {

    suspend fun getShopItem(shopItemId: Int): ShopItem = shopListRepository.getShopItemById(shopItemId)
}