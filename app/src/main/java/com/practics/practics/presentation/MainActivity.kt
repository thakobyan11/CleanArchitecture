package com.practics.practics.presentation

import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.practics.practics.R
import com.practics.practics.domain.ShopItem

class MainActivity : AppCompatActivity() {
    private lateinit var linearLayout:LinearLayout
    private lateinit var name:TextView
    private lateinit var count:TextView

    private lateinit var vm:MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        vm = ViewModelProvider(this)[MainViewModel::class.java]
        linearLayout = findViewById(R.id.linear_layout)

        vm.listShop.observe(this){
            showList(it)
        }
    }

    private fun showList(list: List<ShopItem>) {
        linearLayout.removeAllViews()
        for (shopItem in list){
            val layoutId = if (shopItem.isActive){
                R.layout.shop_list_item
            }else{
                R.layout.shop_list_item_light
            }
            val view = LayoutInflater.from(this).inflate(layoutId,linearLayout,false)
            name = view.findViewById(R.id.name)
            count = view.findViewById(R.id.count)
            name.text = shopItem.name
            count.text = shopItem.count.toString()
            view.setOnLongClickListener {
                vm.changeActiveState(shopItem)
                true
            }
            linearLayout.addView(view)
        }
    }
}