package com.practics.practics.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.practics.practics.data.ShopListRepositoryImpl
import com.practics.practics.domain.DeleteShopItemUseCase
import com.practics.practics.domain.EditShopItemUseCase
import com.practics.practics.domain.GetShopListUseCase
import com.practics.practics.domain.ShopItem

class MainViewModel (application: Application): AndroidViewModel(application) {

    private val repository = ShopListRepositoryImpl(application)

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