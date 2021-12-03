package com.mestabn.aad_playground.ut03.ex02.app

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mestabn.aad_playground.ut03.ex02.data.*
import com.mestabn.aad_playground.ut03.ex02.data.local.dao.*

/**
 * Creamos la base de datos que nos permite trabajar con los dao y entity que tenemos
 * Base de datos SQLite
 */


@Database(
    entities = [PersonEntity::class, PetEntity::class, CarEntity::class, JobEntity::class, PersonJobEntity::class],
    version = 1

)
abstract class Ut03Ex02DataBase : RoomDatabase() {
    //Creamos funciones abstractas por cada uno de los Dao
    abstract fun personDao(): PersonDao
    abstract fun petDao(): PetDao
    abstract fun carDao(): CarDao
    abstract fun jobDao(): JobDao
    abstract fun personJobDao(): PersonJobDao

    /**
     * Necesitamos crear una única instancia de la base de datos. Esto es así porque es muy
     * constoso en recursos de memoria usar varias instancias.
     *
     * A través del patrón de diseño SINGLETON creamos una instancia de la base de datos
     * y nos aseguramos que no se creen más.
     */

    companion object {
        //Asegura de que cuando pase el primer hilo se carga en memoria antes de que pase otro hilo
        @Volatile
        private var instance: Ut03Ex02DataBase? = null


        /**
         * Patron SINGLETON
         * para mantener la instancia en toda la app
         */
        //Le pasamos el contexto de la app para que sea accesible desde cualquier punto
        fun getInstance(applicationContext: Context): Ut03Ex02DataBase {
            return instance ?: synchronized(this) {
                //synchronized es para que no entren dos hilos a la vez
                instance ?: buildDataBase(applicationContext).also { instance = it }
            }
            /*if (instance == null) {
                    instance = buildDataBase(applicationContext)
            }
            return instance as Ut03Ex02DataBase*/
        }

        private fun buildDataBase(applicationContext: Context): Ut03Ex02DataBase {
            return Room
                .databaseBuilder(
                    applicationContext,
                    Ut03Ex02DataBase::class.java,
                    "db-ut03-ex02"
                ).build()
        }
    }


}