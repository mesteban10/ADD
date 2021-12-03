package com.mestabn.aad_playground.ut02

interface Model {
    fun getId(): Int
}

data class UserModel(val userId: Int, val name: String, val surname: String) : Model {
    override fun getId(): Int = userId
}