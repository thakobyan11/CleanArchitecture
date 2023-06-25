package com.practics.practics.presentation

import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.practics.practics.R
import com.practics.practics.domain.ShopItem

class MainActivity : AppCompatActivity() {
    private lateinit var linearLayout: LinearLayout
    private lateinit var adapter: ShopListAdapter

    private lateinit var vm: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpAdapter()
        vm = ViewModelProvider(this)[MainViewModel::class.java]
        linearLayout = findViewById(R.id.linear_layout)

        vm.listShop.observe(this) {
            adapter.shopList = it
        }
    }

    private fun setUpAdapter() {
        adapter = ShopListAdapter()
        val rcv: RecyclerView = findViewById(R.id.rcv)
        rcv.adapter = adapter
        rcv.recycledViewPool.setMaxRecycledViews(
            ShopListAdapter.VIEW_TYPE_IS_ACTIVE,
            ShopListAdapter.MAX_POOL_COUNT
        )
        rcv.recycledViewPool.setMaxRecycledViews(
            ShopListAdapter.VIEW_TYPE_IS_NOT_ACTIVE,
            ShopListAdapter.MAX_POOL_COUNT
        )
    }
}