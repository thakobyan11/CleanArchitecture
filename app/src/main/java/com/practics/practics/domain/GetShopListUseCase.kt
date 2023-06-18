package com.practics.practics.domain

class GetShopListUseCase (private val shopListRepository: ShopListRepository) {

    fun getShopList():List<ShopItem> = shopListRepository.getShopList()
}