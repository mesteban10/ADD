package com.mestabn.aad_playground.ut03.ex02.dominio

interface PersonRepository {

    fun savePerson(personModel: PersonModel)
    fun fetchAll(): List<PersonModel>?

}