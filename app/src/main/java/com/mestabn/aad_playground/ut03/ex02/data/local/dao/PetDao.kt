package com.mestabn.aad_playground.ut03.ex02.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mestabn.aad_playground.ut03.ex02.data.PersonEntity
import com.mestabn.aad_playground.ut03.ex02.data.PetEntity

@Dao
interface PetDao {

    @Query("SELECT * FROM pet")
    fun findAll(): List<PetEntity>

    @Insert
    fun insert(petEntity: PetEntity): Long

    @Insert
    fun insertPeopleAndPet(
        personEntity: PersonEntity,
        petEntity: PetEntity
    )

    @Insert
    fun insert(vararg petEntity: PetEntity)


}