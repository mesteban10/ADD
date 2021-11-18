package com.mestabn.aad_playground.ut03.ex03_alerts.app.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mestabn.aad_playground.ut03.ex03_alerts.data.local.db.dao.AlertDao
import com.mestabn.aad_playground.ut03.ex03_alerts.data.local.db.dao.FileDao
import com.mestabn.aad_playground.ut03.ex03_alerts.data.local.db.entity.AlertEntity
import com.mestabn.aad_playground.ut03.ex03_alerts.data.local.db.entity.FileEntity


@Database(
    entities = [AlertEntity::class, FileEntity::class],
    version = 1

)
abstract class Ut03Ex03DataBase : RoomDatabase() {

    abstract fun alertDao(): AlertDao
    abstract fun fileDao(): FileDao


    companion object {
        @Volatile
        private var instance: Ut03Ex03DataBase? = null


        fun getInstance(applicationContext: Context): Ut03Ex03DataBase {
            return instance ?: synchronized(this) {
                instance ?: buildDataBase(applicationContext).also { instance = it }
            }
            /*if (instance == null) {
                    instance = buildDataBase(applicationContext)
            }
            return instance as Ut03Ex02DataBase*/
        }

        private fun buildDataBase(applicationContext: Context): Ut03Ex03DataBase {
            return Room
                .databaseBuilder(
                    applicationContext,
                    Ut03Ex03DataBase::class.java,
                    "db-ut03-ex03"
                ).build()
        }
    }


}