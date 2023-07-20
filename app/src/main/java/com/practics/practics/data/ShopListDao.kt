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
    fun addShopItem(shopItemDB: ShopItemDB)

    @Query("DELETE FROM shop_item WHERE id=:shopItemID")
    fun deleteShopItem(shopItemID:Int)

    @Query("SELECT * FROM shop_item WHERE id=:shopItemID LIMIT 1")
    fun getShopItemById(shopItemID: Int):ShopItemDB
}