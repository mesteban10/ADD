package com.mestabn.aad_playground.ut_01.exercise01.data

interface DataSource {
    fun save(id: String, content: String)
    fun delete(id: String)
    fun show(id: String): String?
    fun showAll(): MutableList<String>
}


