package com.practics.practics.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.practics.practics.data.ShopListRepositoryImpl
import com.practics.practics.domain.*
import java.lang.Exception

class ShopItemViewModel : ViewModel() {
    private val repository = ShopListRepositoryImpl()
    private val addShopItemUseCase = AddShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)
    private val getShopItemUseCase = GetShopItemUseCase(repository)

    private val _errorInputName = MutableLiveData<Boolean>()
     val errorInputName : LiveData<Boolean>
         get() = _errorInputName

    private val _errorInputCount = MutableLiveData<Boolean>()
     val errorInputCount : LiveData<Boolean>
         get() = _errorInputCount

    private val _shopItem = MutableLiveData<ShopItem>()
     val shopItem : LiveData<ShopItem>
         get() = _shopItem

    private val _shouldCloseScreen = MutableLiveData<Unit>()
     val shouldCloseScreen : LiveData<Unit>
         get() = _shouldCloseScreen

    fun editShopItem(inputName : String? , inputCount : String?){
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validateInput(name,count)
        if (fieldsValid){
            _shopItem.value?.let {
                val shopItem = it.copy(name = name, count = count)
                editShopItemUseCase.editShopItem(shopItem)
                finishWork()
            }
        }
    }

    fun addShopItem(inputName : String? , inputCount : String?){
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validateInput(name,count)
        if (fieldsValid){
            val shopItem = ShopItem(name = name, count = count, isActive = true)
            addShopItemUseCase.addShopItem(shopItem)
            finishWork()
        }
    }

    fun getShopItem(shopItemId: Int){
        val item = getShopItemUseCase.getShopItem(shopItemId)
        _shopItem.value = item
    }

    private fun parseName(inputName : String?):String{
        return inputName?.trim() ?:""
    }

    private fun parseCount(inputCount : String?):Int{
        return try {
            inputCount?.trim()?.toInt() ?:0
        }catch (e:Exception){
            0
        }
    }

    private fun validateInput(name:String,count :Int):Boolean{
        var result = true
        if (name.isBlank()){
            _errorInputName.value = true
            result = false
        }
        if (count <= 0){
            _errorInputCount.value = true
            result = false
        }
        return result
    }

    fun resetErrorCount(){
        _errorInputCount.value = false
    }

    fun resetErrorName(){
        _errorInputName.value = false
    }

    private fun finishWork(){
        _shouldCloseScreen.value = Unit
    }
}