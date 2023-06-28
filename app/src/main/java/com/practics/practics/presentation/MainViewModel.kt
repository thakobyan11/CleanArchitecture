package com.practics.practics.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.practics.practics.data.ShopListRepositoryImpl
import com.practics.practics.domain.*

class MainViewModel: ViewModel() {

    private val repository = ShopListRepositoryImpl()

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val listShop = getShopListUseCase.getShopList()


    fun deleteShopItem(item: ShopItem){
        deleteShopItemUseCase.deleteShopItem(item)
    }

    fun changeActiveState(item: ShopItem){
        val newItem = item.copy(isActive = !item.isActive)
        editShopItemUseCase.editShopItem(newItem)
    }

}