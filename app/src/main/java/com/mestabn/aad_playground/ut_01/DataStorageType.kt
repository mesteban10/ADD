package com.mestabn.aad_playground.ut_01

import androidx.appcompat.app.AppCompatActivity
import java.io.File

class DataStorageType(private val activity: AppCompatActivity) {

    /**
     * Fichero especifico de la app en el directorio de la app
     * las app ecternas no pueden acceder a estos ficheros
     * no necesitamos permisos especiales
     * los ficheros desaparecen si se desinstala la pp
     */

    fun privateFile() {
        val privateFile = File(activity.filesDir, "private.txt")
        privateFile.writeText("Fichero privado en el directorio de la app")
    }

    /**
     * Private cache
     */
    fun privateFileCache() {
        val privateFileCache = File(activity.cacheDir, "private_cache.txt")
        privateFileCache.writeText("Fichero cache privado")
    }

    //sdcard -> Android -> data -> mi id -> files AQUI ESTAN DIRECTORIOS EXTERNOS
    fun privateExternalFile() {
        val externalFile = File(activity.getExternalFilesDir("path_directory"), "external.txt")
        externalFile.writeText("Privado en un almacenamiento  ")
    }

    fun privateExternalCacheFile() {
        val cachExternalFile = File(activity.externalCacheDir, "cache_external_dir.txt")
        cachExternalFile.writeText("External Cache Privado")
    }
}