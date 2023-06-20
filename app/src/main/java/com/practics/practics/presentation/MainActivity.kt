package com.practics.practics.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.practics.practics.R

class MainActivity : AppCompatActivity() {

    private lateinit var vm:MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        vm = ViewModelProvider(this)[MainViewModel::class.java]
        vm.getShopList()
        Log.d("TAG","${vm.listShop.value}")
    }
}