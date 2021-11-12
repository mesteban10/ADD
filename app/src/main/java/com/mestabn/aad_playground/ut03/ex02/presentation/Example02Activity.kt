package com.mestabn.aad_playground.ut03.ex02.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.mestabn.aad_playground.R
import com.mestabn.aad_playground.ut03.ex02.data.PersonDataRepository
import com.mestabn.aad_playground.ut03.ex02.data.local.PersonLocalSource
import com.mestabn.aad_playground.ut03.ex02.dominio.*

class Example02Activity : AppCompatActivity() {

    private val TAG = Example02Activity::class.java.simpleName


    //Lazy: crea el repositorio pero no le inicializa. Solo cuando llamo a la variable se inicializa
    //Lo ponemos asi en vez de inicializarlo en el on create
    private val repository: PersonRepository by lazy {
        PersonDataRepository(PersonLocalSource(applicationContext))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example02)
        executeQuery()
    }


    private fun executeQuery() {
        Thread {
            repository.savePerson(
                PersonModel(
                    1, "Pepe", 10, "Calle Mayor",
                    PetModel(3, "Pepe", 1),
                    mutableListOf(CarModel(1, "Seat", "127"),
                        CarModel(2, "Ford", "Malaga")
                    ),
                    mutableListOf(JobModel(1, "Teacher"),
                        JobModel(2, "Android Developer")
                    )
                )
            )
            val people = repository.fetchAll()
            Log.d("@dev", "$people")
        }.start()
    }

}