package com.mestabn.aad_playground.ut03.ex02.presentation

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mestabn.aad_playground.ut03.ex02.data.PersonDataRepository
import com.mestabn.aad_playground.ut03.ex02.data.local.PersonLocalSource
import com.mestabn.aad_playground.ut03.ex02.dominio.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Example02ViewModel : ViewModel() {


    fun saveUsers(applicationContext: Context) {

        val bd = PersonDataRepository(PersonLocalSource(applicationContext))

        viewModelScope.launch(Dispatchers.Main) {
            bd.savePerson(
                PersonModel(
                    1, "Pepe", 10, "Calle Mayor",
                    PetModel(3, "Pepe", 1),
                    mutableListOf(
                        CarModel(1, "Seat", "127"),
                        CarModel(2, "Ford", "Malaga")
                    ),
                    mutableListOf(
                        JobModel(1, "Teacher"),
                        JobModel(2, "Android Developer")
                    )
                )
            )
        }

    }

    fun fetchAll(applicationContext: Context) {

        val bd = PersonDataRepository(PersonLocalSource(applicationContext))

        viewModelScope.launch(Dispatchers.Main) {
            val people = bd.fetchAll()
            Log.d("@dev", "$people")
        }

    }


}



