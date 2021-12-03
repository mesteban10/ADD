package com.mestabn.aad_playground.ut03.ex03.app.storage


class MemLocalStorage<T: LocalModel> : LocalStorage<T> {
    override fun fetch(modelId: String, typeClass: Class<T>): T? {
        TODO("Not yet implemented")
    }

    override fun save(model: T, typeClass: Class<T>) {
        TODO("Not yet implemented")
    }
}