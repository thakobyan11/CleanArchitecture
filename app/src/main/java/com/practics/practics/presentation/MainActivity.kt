package com.practics.practics.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.practics.practics.R

class MainActivity : AppCompatActivity() {
    private lateinit var shopListAdapter: ShopListAdapter

    private lateinit var vm: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpAdapter()
        vm = ViewModelProvider(this)[MainViewModel::class.java]

        vm.listShop.observe(this) {
            shopListAdapter.submitList(it)
        }
    }

    private fun setUpAdapter() {
        shopListAdapter = ShopListAdapter()
        val rcv: RecyclerView = findViewById(R.id.rcv)
        rcv.adapter = shopListAdapter
        rcv.recycledViewPool.setMaxRecycledViews(
            ShopListAdapter.VIEW_TYPE_IS_ACTIVE,
            ShopListAdapter.MAX_POOL_COUNT
        )
        rcv.recycledViewPool.setMaxRecycledViews(
            ShopListAdapter.VIEW_TYPE_IS_NOT_ACTIVE,
            ShopListAdapter.MAX_POOL_COUNT
        )
        setUpLongClick()
        setUpOnClick()
        val callBack = object :
            ItemTouchHelper.SimpleCallback(
                0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = shopListAdapter.currentList[viewHolder.adapterPosition]
                vm.deleteShopItem(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callBack)
        itemTouchHelper.attachToRecyclerView(rcv)
    }

    private fun setUpOnClick() {
        shopListAdapter.shopItemClickListener = {
            Log.d("TAG", "CLICK")
        }
    }

    private fun setUpLongClick() {
        shopListAdapter.shopItemLongClickListener = {
            vm.changeActiveState(it)
        }
    }
}
