package com.mestabn.aad_playground.ut03.ex03_alerts.app.storage

import androidx.appcompat.app.AppCompatActivity
import com.mestabn.aad_playground.ut03.ex03_alerts.app.serializer.JsonSerializer
import java.io.File

class FileLocalStorage<T : LocalModel>(
    private val activity: AppCompatActivity,
    private val jsonSerializer: JsonSerializer
) : LocalStorage<T> {

    private val file = File(activity.filesDir, "aad_ut03_ex03.txt")

    override fun fetch(modelId: String, typeClass: Class<T>): T? {
        if (file.exists()) {
            return jsonSerializer.fromJson(file.readText(), typeClass)
        }
        return null
    }

    override fun save(model: T, typeClass: Class<T>) {
        file.writeText(jsonSerializer.toJson(model, typeClass))
    }
}

