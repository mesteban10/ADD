package com.mestabn.aad_playground.ut_01.exercise01.data

class MemDataSource() : DataSource {

    private val memDataSource: MutableMap<String, String> = mutableMapOf()

    override fun save(id: String, content: String) {
        memDataSource[id] = content
    }

    override fun delete(id: String) {
        memDataSource.remove(id)
    }

    override fun show(id: String): String? = memDataSource[id]

    override fun showAll(): MutableList<String> = memDataSource.keys.toMutableList()

}