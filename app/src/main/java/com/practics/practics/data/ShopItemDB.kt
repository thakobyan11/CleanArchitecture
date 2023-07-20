package com.practics.practics.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("shop_item")
data class ShopItemDB(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val name:String,
    val count:Int,
    val isActive:Boolean
)
