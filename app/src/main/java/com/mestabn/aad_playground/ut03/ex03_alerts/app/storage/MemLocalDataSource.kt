package com.mestabn.aad_playground.ut03.ex03_alerts.app.storage

class MemLocalStorage<T : LocalModel> : LocalStorage<T> {

    private val memDataStorage: MutableList<T> = mutableListOf()

    override fun fetch(modelId: String, typeClass: Class<T>): T? {
        memDataStorage.forEach {
            if (it.getId() == modelId) {
                return it
            }
        }
        return null
    }

    override fun save(model: T, typeClass: Class<T>) {
        memDataStorage.add(model)
    }
}
