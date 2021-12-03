package com.mestabn.aad_playground.ut03.ex01.data

import androidx.room.*


/**
 * Patron data access object, esta clase solo tiene funciones
 */

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getAll(): List<UserEntity>

    @Query("SELECT * FROM user WHERE id= :id")
    fun findById(id: Int): UserEntity?

    @Query("SELECT * FROM user WHERE id IN (:userIds)")
    fun findAllByIds(userIds: IntArray): List<UserEntity>

    @Query("SELECT * FROM user WHERE name LIKE :name  AND username LIKE :username LIMIT 1 ")
    fun findByName(name:String, username:String): UserEntity?


    //Añadimos registro a la tabla
    @Insert
    fun insert(userEntity: UserEntity)

    @Delete
    fun delete(userEntity: UserEntity)

    @Update
    fun update(userEntity: UserEntity)


}