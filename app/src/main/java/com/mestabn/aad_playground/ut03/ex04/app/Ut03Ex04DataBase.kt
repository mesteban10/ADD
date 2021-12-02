package com.mestabn.aad_playground.ut03.ex04.app

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mestabn.aad_playground.ut03.ex02.app.Ut03Ex02DataBase
import com.mestabn.aad_playground.ut03.ex02.data.*
import com.mestabn.aad_playground.ut03.ex02.data.local.dao.*
import com.mestabn.aad_playground.ut03.ex04.data.local.dao.CustomerDao
import com.mestabn.aad_playground.ut03.ex04.data.local.dao.InvoiceDao
import com.mestabn.aad_playground.ut03.ex04.data.local.dao.InvoiceLinesDao
import com.mestabn.aad_playground.ut03.ex04.data.local.dao.ProductDao
import com.mestabn.aad_playground.ut03.ex04.data.local.entity.CustomerEntity
import com.mestabn.aad_playground.ut03.ex04.data.local.entity.InvoiceEntity
import com.mestabn.aad_playground.ut03.ex04.data.local.entity.InvoiceLinesEntity
import com.mestabn.aad_playground.ut03.ex04.data.local.entity.ProductEntity


@Database(
    entities = [CustomerEntity::class, ProductEntity::class, InvoiceLinesEntity::class, InvoiceEntity::class],
    version = 1

)
abstract class Ut03Ex04DataBase : RoomDatabase() {

    abstract fun customerDao(): CustomerDao
    abstract fun productDao(): ProductDao
    abstract fun invoiceLinesDao(): InvoiceLinesDao
    abstract fun invoiceDao(): InvoiceDao

    companion object {
        @Volatile
        private var instance: Ut03Ex04DataBase? = null


        /**
         * Patron SINGLETON
         * para mantener la instancia en toda la app
         */

        fun getInstance(applicationContext: Context): Ut03Ex04DataBase {
            return instance ?: synchronized(this) {
                instance ?: buildDataBase(applicationContext).also { instance = it }
            }

        }

        private fun buildDataBase(applicationContext: Context): Ut03Ex04DataBase {
            return Room
                .databaseBuilder(
                    applicationContext,
                    Ut03Ex04DataBase::class.java,
                    "db-ut03-ex04"
                ).build()
        }
    }


    }
