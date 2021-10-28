package com.mestabn.aad_playground.ut_01.exercise01.data

import java.io.File

class FileDataSource(private val path: String) : DataSource {

    lateinit var file: File

    init {
        file = createFile()
    }

    private fun createFile(): File {
        val file = File(path)
        if (!file.exists()) {
            file.mkdir()
        }
        return file
    }

    override fun save(id: String, content: String) {
        getFile(id).writeText(content)
    }

    override fun delete(id: String) {
        val file = getFile(id)
        if (file.exists()) {
            file.delete()
        }
    }

    override fun show(id: String): String = getFile(id).readText()

    override fun showAll(): MutableList<String> = file.list()?.toMutableList() ?: mutableListOf()

    private fun getFile(id: String) = File(file, id)
}

