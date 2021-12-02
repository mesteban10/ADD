package com.mestabn.aad_playground.ut03.ex04.data.local.dao

import androidx.room.*

import com.mestabn.aad_playground.ut03.ex04.data.local.entity.CustomerEntity
import com.mestabn.aad_playground.ut03.ex04.data.local.entity.ProductEntity

@Dao
interface ProductDao {
    @Insert
    fun insert(productEntity: ProductEntity): Long

    @Update
    fun update(vararg productEntity: ProductEntity)

    @Delete
    fun delete(vararg productEntity: ProductEntity)

    @Transaction
    @Query("SELECT * FROM product")
    fun findAll(): List<ProductEntity>

    @Query("SELECT * FROM product WHERE id = :productId")
    fun findById(productId: Int): ProductEntity
}