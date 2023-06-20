package com.practics.practics.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.practics.practics.data.ShopListRepositoryImpl
import com.practics.practics.domain.*

class MainViewModel: ViewModel() {

    val listShop = MutableLiveData<List<ShopItem>>()

    private val repository = ShopListRepositoryImpl()

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    fun getShopList(){
        listShop.value = getShopListUseCase.getShopList()
    }

    fun deleteShopItem(item: ShopItem){
        deleteShopItemUseCase.deleteShopItem(item)
        getShopList()
    }

    fun editShopItem(item: ShopItem){
        editShopItemUseCase.editShopItem(item)
    }

    fun changeActiveState(item: ShopItem){
        val newItem = item.copy(isActive = !item.isActive)
        editShopItemUseCase.editShopItem(newItem)
        getShopList()
    }
}