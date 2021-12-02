package com.mestabn.aad_playground.ut03.ex04.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.mestabn.aad_playground.R
import com.mestabn.aad_playground.ut03.ex02.presentation.Example02ViewModel

class Ut03Ex04Activity : AppCompatActivity() {

    private val viewModel :CustomerViewModel by lazy{
        CustomerViewModel(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ut03_ex04)
        executeQuery()
    }

    private fun executeQuery(){
        viewModel.updateCustomer()

    }
}