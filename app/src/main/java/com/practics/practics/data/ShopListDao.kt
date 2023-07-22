package com.practics.practics.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ShopListDao {

    @Query("SELECT * FROM shop_item")
    fun getShopList():LiveData<List<ShopItemDB>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addShopItem(shopItemDB: ShopItemDB)

    @Query("DELETE FROM shop_item WHERE id=:shopItem")
    suspend fun deleteShopItem(shopItem:Int)

    @Query("SELECT * FROM shop_item WHERE id=:shopItemID LIMIT 1")
    suspend fun getShopItemById(shopItemID: Int):ShopItemDB
}