package com.practics.practics.domain

class EditShopItemUseCase(private val shopListRepository: ShopListRepository) {

    suspend fun editShopItem(shopItem: ShopItem){
            shopListRepository.editShopItem(shopItem)
    }
}