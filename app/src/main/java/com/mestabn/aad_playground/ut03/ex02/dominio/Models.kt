package com.mestabn.aad_playground.ut03.ex02.dominio


/**
 * Relation 1:1, un cliente solo una mascota
 * NUNCA SE ACOPLA NADA DE ROOM EN EL DOMINIO
 */
data class PersonModel(
    val id: Int,
    val name: String,
    val age: Int,
    val address: String,
    val petModel: PetModel,
    val carModel: MutableList<CarModel>,
    val jobModel: MutableList<JobModel>

)

data class PetModel(val id: Int, val name: String, val age: Int)

data class CarModel(val id: Int, val brand: String, val model: String)

data class JobModel(val id: Int, val name: String)