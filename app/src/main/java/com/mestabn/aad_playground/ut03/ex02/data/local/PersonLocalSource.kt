package com.mestabn.aad_playground.ut03.ex02.data.local

import android.content.Context
import com.mestabn.aad_playground.ut03.ex02.app.Ut03Ex02DataBase
import com.mestabn.aad_playground.ut03.ex02.data.*
import com.mestabn.aad_playground.ut03.ex02.dominio.PersonModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


/**
 * Clase que maneja la clase de datos (u otra fuente de datos)
 * Recupera la informacion de la base de datos
 */
class PersonLocalSource(applicationContext: Context) {

    private val db = Ut03Ex02DataBase.getInstance(applicationContext)

    init {
        /*
            Workaround (solución alterna)
            Para vaciar las tablas y esperar un tiempo para que le de tiempo a vaciarse.
         */
        Thread {
            db.clearAllTables()
        }.start()
        Thread.sleep(2000)
    }

    suspend fun findAll(): List<PersonModel> = withContext(Dispatchers.IO){
        val personAndPetsAndCars = db.personDao().getPersonAndPetAndCarsAndJobs()
        return@withContext personAndPetsAndCars.map { element -> element.toModel() }
    }


    suspend fun save(personModel: PersonModel)= withContext(Dispatchers.IO) {
        db.personDao().insertPeopleAndPetAndCarsAndJobs(
                PersonEntity.toEntity(personModel),
                PetEntity.toEntity(personModel.petModel, personModel.id),
                CarEntity.toEntity(personModel.carModel, personModel.id),
                JobEntity.toEntity(personModel.jobModel),
                personModel.jobModel.map { PersonJobEntity.toEntity(personModel.id, it.id) }
        )
    }

    fun save2way(personModel: PersonModel) {
        db.runInTransaction {
            val personId = db.personDao().insert(PersonEntity.toEntity(personModel))
            db.petDao().insert(PetEntity.toEntity(personModel.petModel, personId.toInt()))
            db.carDao().insert(CarEntity.toEntity(personModel.carModel, personId.toInt()))
            val jobIds = db.jobDao().insert(JobEntity.toEntity(personModel.jobModel))
            db.personJobDao().insert(jobIds.map { jobId ->
                PersonJobEntity.toEntity(personId.toInt(), jobId.toInt())
            })
        }
    }
}