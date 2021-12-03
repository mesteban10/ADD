package com.mestabn.aad_playground.ut03.ex01.data

import androidx.room.Database
import androidx.room.RoomDatabase


/**
 * Crea, versoina, la base de datos
 */

@Database(entities = [UserEntity::class], version = 1)

//Tiene una metodo no implementado, por eso es una clase abstracta

abstract class AppDataBase  : RoomDatabase(){
    abstract fun userDao(): UserDao
}