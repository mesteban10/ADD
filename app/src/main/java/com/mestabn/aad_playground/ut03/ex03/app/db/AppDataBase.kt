package com.mestabn.aad_playground.ut03.ex03.app.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mestabn.aad_playground.ut03.ex03.data.local.db.AlertDao
import com.mestabn.aad_playground.ut03.ex03.data.local.db.AlertEntity
import com.mestabn.aad_playground.ut03.ex03.data.local.db.FileDao
import com.mestabn.aad_playground.ut03.ex03.data.local.db.FileEntity


@Database(
        entities = [AlertEntity::class, FileEntity::class],
        version = 1,
        exportSchema = false
)
abstract class Ut03Ex03Database : RoomDatabase() {

    abstract fun alertDao(): AlertDao
    abstract fun fileDao(): FileDao

    /**
     * Necesitamos crear una única instancia de la base de datos. Esto es así porque es muy
     * constoso en recursos de memoria usar varias instancias.
     *
     * A través del patrón de diseño SINGLETON creamos una instancia de la base de datos
     * y nos aseguramos que no se creen más.
     */
    companion object {
        @Volatile
        private var instance: Ut03Ex03Database? = null

        fun getInstance(applicationContext: Context): Ut03Ex03Database {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(applicationContext).also { instance = it }
            }
        }

        private fun buildDatabase(applicationContext: Context): Ut03Ex03Database {
            return Room.databaseBuilder(
                    applicationContext,
                    Ut03Ex03Database::class.java,
                    "db-ut03-ex03-alerts"
            ).build()
        }
    }
}