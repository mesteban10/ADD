package com.mestabn.aad_playground.ut03.ex02.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.mestabn.aad_playground.R
import com.mestabn.aad_playground.ut03.ex02.data.PersonDataRepository
import com.mestabn.aad_playground.ut03.ex02.data.local.PersonLocalSource
import com.mestabn.aad_playground.ut03.ex02.dominio.*

class Example02Activity : AppCompatActivity() {

    private val TAG = Example02Activity::class.java.simpleName

    private val viewModel by viewModels<Example02ViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example02)
        executeQuery()
    }


    private fun executeQuery() {
        viewModel.saveUsers(applicationContext)
        viewModel.fetchAll(applicationContext)
    }

}