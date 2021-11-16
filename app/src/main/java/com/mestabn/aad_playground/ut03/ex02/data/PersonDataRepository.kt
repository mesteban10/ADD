package com.mestabn.aad_playground.ut03.ex02.data

import com.mestabn.aad_playground.ut03.ex02.data.local.PersonLocalSource
import com.mestabn.aad_playground.ut03.ex02.dominio.PersonModel
import com.mestabn.aad_playground.ut03.ex02.dominio.PersonRepository


/**
 * Implementa la interfaz declarada en el dominio
 */
class PersonDataRepository(private val personLocalSource: PersonLocalSource) : PersonRepository {

    override suspend fun savePerson(personModel: PersonModel) {
        personLocalSource.save(personModel)
    }

    override suspend fun fetchAll(): List<PersonModel>? = personLocalSource.findAll()




}