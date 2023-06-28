package com.practics.practics.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.practics.practics.R

class MainActivity : AppCompatActivity() {

    private lateinit var vm:MainViewModel
    private var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        vm = ViewModelProvider(this)[MainViewModel::class.java]
        vm.listShop.observe(this){
        Log.d("TAG","${vm.listShop.value}")
            if (count == 0){
                count++
                vm.changeActiveState(it[0])
            }
        }
    }
}