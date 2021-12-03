package com.mestabn.aad_playground.ut03.ex02.data

import androidx.room.*
import com.mestabn.aad_playground.ut03.ex02.dominio.CarModel
import com.mestabn.aad_playground.ut03.ex02.dominio.JobModel
import com.mestabn.aad_playground.ut03.ex02.dominio.PersonModel
import com.mestabn.aad_playground.ut03.ex02.dominio.PetModel


/**
 * Clase que trabaja exclusivamente con ROOM
 */

@Entity(tableName = "person")
data class PersonEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "age") val age: Int
) {
    fun toModel(petEntity: PetEntity, carsEntity: List<CarEntity>, jobsEntity: List<JobEntity>) =
        PersonModel(
            id,
            name,
            age,
            "",
            petEntity.toModel(),
            carsEntity.map { it.toModel() }.toMutableList(),
            jobsEntity.map { it.toModel() }.toMutableList()
        )

    companion object {
        fun toEntity(personModel: PersonModel) =
            PersonEntity(personModel.id, personModel.name, personModel.age)
    }
}

    @Entity(tableName = "pet")
    data class PetEntity(
        @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int,
        @ColumnInfo(name = "name") val name: String,
        @ColumnInfo(name = "age") val age: Int,
        @ColumnInfo(name = "person_id") val personId: Int
    ) {
        fun toModel() = PetModel(id, name, age)

        companion object {
            fun toEntity(petModel: PetModel, personId: Int) =
                PetEntity(petModel.id, petModel.name, petModel.age, personId)
        }
    }

    /**
     * Una persona sólo puede tener una única mascota.
     *
     * Relación 1:1 donde Person cede su clave primaria a Pet.
     */
    data class PersonAndPet(
        @Embedded val personEntity: PersonEntity, //Entidad Principal
        @Relation(
            parentColumn = "id", //clave primaria de la entidad Person.
            entityColumn = "person_id" //clave foránea de la entidad Pet.
        ) val petEntity: PetEntity, //Entidad que recibe la clave de otra entidad
    )

    /**
     * Una persona puede tener de 0 a N coches
     */
    @Entity(tableName = "car")
    data class CarEntity(
        @PrimaryKey @ColumnInfo(name = "id") val id: Int,
        @ColumnInfo(name = "brand") val brand: String,
        @ColumnInfo(name = "model") val model: String,
        @ColumnInfo(name = "person_id") val personId: Int,
    ) {
        fun toModel() = CarModel(id, brand, model)

        companion object {
            fun toEntity(carsModel: List<CarModel>, personId: Int) = carsModel.map {
                CarEntity(it.id, it.brand, it.model, personId)
            }
        }
    }

    /**
     * Una persona puede tener de 1:N coches.
     *
     * Relación 1:N donde Person cede su clave primaria a Car.
     */
    data class PersonAndPetAndCar(
        @Embedded val personEntity: PersonEntity, //Entidad Principal

        @Relation(
            parentColumn = "id", //clave primaria de la entidad Person.
            entityColumn = "person_id" //clave foránea de la entidad Pet.
        ) val petEntity: PetEntity, //Entidad que recibe la clave de otra entidad

        @Relation(
            parentColumn = "id", //clave primaria de la entidad Person.
            entityColumn = "person_id" //clave foránea de la entidad Car.
        ) val carEntities: List<CarEntity>, //Entidad que recibe la clave de otra entidad
    )

    @Entity(tableName = "job")
    data class JobEntity(
        @PrimaryKey @ColumnInfo(name = "id") val id: Int,
        @ColumnInfo(name = "name") val name: String,
    ) {
        fun toModel() = JobModel(id, name)

        companion object {
            fun toEntity(jobsModel: List<JobModel>) = jobsModel.map {
                JobEntity(it.id, it.name)
            }
        }
    }

    /**
     * Una persona puede tener de 1 a N jobs y un job puede tener de N personas.
     *
     * Relación N:M se crea una nueva tabla
     */
    @Entity(tableName = "person_job", primaryKeys = ["person_id", "job_id"])
    data class PersonJobEntity(
        @ColumnInfo(name = "person_id") val personId: Int,
        @ColumnInfo(name = "job_id") val jobId: Int
    ) {

        companion object {
            fun toEntity(personId: Int, jobId: Int) = PersonJobEntity(personId, jobId)
        }
    }

    data class PersonAndPetAndCarAndJob(
        @Embedded val personEntity: PersonEntity, //Entidad Principal

        @Relation(
            parentColumn = "id", //clave primaria de la entidad Person.
            entityColumn = "person_id" //clave foránea de la entidad Pet.
        ) val petEntity: PetEntity, //Entidad que recibe la clave de otra entidad

        @Relation(
            parentColumn = "id", //clave primaria de la entidad Person.
            entityColumn = "person_id" //clave foránea de la entidad Car.
        ) val carEntities: List<CarEntity>, //Entidad que recibe la clave de otra entidad

        @Relation(
            parentColumn = "id", //clave primaria entidad principal PersonEntity
            entityColumn = "id", //clave primaria entidad secundaria JobEntity.
            associateBy = Junction(
                value = PersonJobEntity::class,
                parentColumn = "person_id", //clave entidad principal de PersonJobEntity
                entityColumn = "job_id"
            ) //clave entidad secundaria de PersonJobEntity
        ) val jobEntities: List<JobEntity>, //Entidad que recibe la clave de otra entidad
    ) {
        fun toModel() = personEntity.toModel(petEntity, carEntities, jobEntities)
    }

