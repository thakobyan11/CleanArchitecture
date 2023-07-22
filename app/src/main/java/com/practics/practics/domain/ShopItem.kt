package com.practics.practics.domain

data class ShopItem(
    var id:Int = UNDEFINED_ID,
    val name:String,
    val count:Int,
    val isActive:Boolean
){
    companion object{
        const val UNDEFINED_ID = 0
    }
}


