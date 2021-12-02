package com.mestabn.aad_playground.ut03.ex04.data.local.dao

import androidx.room.*

import com.mestabn.aad_playground.ut03.ex04.data.local.entity.CustomerEntity

@Dao
interface CustomerDao {
    @Insert
    fun insert(customerEntity: CustomerEntity): Long

    @Update
    fun update(vararg customerEntity: CustomerEntity)

    @Delete
    fun delete(vararg customerEntity: CustomerEntity)

    @Transaction
    @Query("SELECT * FROM customer")
    fun findAll(): List<CustomerEntity>

    @Query("SELECT * FROM customer WHERE id = :customerId")
    fun findById(customerId: Int): CustomerEntity?
}