package com.mestabn.aad_playground.ut03.ex02.dominio

interface PersonRepository {

    suspend fun savePerson(personModel: PersonModel)
    suspend fun fetchAll(): List<PersonModel>?

}