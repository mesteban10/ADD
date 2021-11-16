package com.mestabn.aad_playground.ut03.ex02.data.local.dao

import androidx.room.*
import com.mestabn.aad_playground.ut03.ex02.data.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Forma de acceso a la clase de datos
 */


@Dao
interface PersonDao {

    @Transaction
    @Query("SELECT * FROM person")
    fun findAll(): List<PersonEntity>

    @Query("SELECT * FROM person WHERE id = :personId")
    fun findById(personId: Int): PersonAndPetAndCarAndJob

    @Insert
    fun insert(personEntity: PersonEntity): Long

    @Insert
    fun insertPeopleAndPetAndCarsAndJobs(
        personEntity: PersonEntity,
        petEntity: PetEntity,
        carsEntity: List<CarEntity>,
        jobsEntities: List<JobEntity>,
        joinEntities: List<PersonJobEntity>
    )

    @Delete
    fun delete(vararg personEntity: PersonEntity)

    @Update
    fun update(vararg personEntity: PersonEntity)

    /**
     * Función que me devuelve la relación 1:1 entre Person y Pet
     */
    @Transaction
    @Query("SELECT * FROM person")
    fun getPersonAndPets(): List<PersonAndPet>

    /**
     * Función que me devuelve:
     * - La relación 1:1 entre Person y Pet
     * - La relación 1:N entre Person y Cars
     */
    @Transaction
    @Query("SELECT * FROM person")
    fun getPersonAndPetAndCars(): List<PersonAndPetAndCar>


    /**
     * Función que me devuelve:
     * - La relación 1:1 entre Person y Pet
     * - La relación 1:N entre Person y Cars
     * - La relación N:M entre Person y Jobs
     */
    @Transaction
    @Query("SELECT * FROM person")
    fun getPersonAndPetAndCarsAndJobs(): List<PersonAndPetAndCarAndJob>

}