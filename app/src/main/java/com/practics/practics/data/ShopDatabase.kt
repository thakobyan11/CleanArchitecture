package com.practics.practics.data

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ShopItemDB::class], version = 1, exportSchema = false)
abstract class ShopDatabase : RoomDatabase() {

    abstract fun getShopListDao():ShopListDao

    companion object {

        private var INSTANCE: ShopDatabase? = null
        private const val DB_NAME = "shop_item.db"

        fun getInstance(application: Application): ShopDatabase {
            INSTANCE?.let {
                return it
            }
            synchronized(Any()) {
                INSTANCE?.let {
                    return it
                }
                val db = Room.databaseBuilder(
                    application,
                    ShopDatabase::class.java,
                    DB_NAME
                )
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = db
                return db
            }
        }
    }
}