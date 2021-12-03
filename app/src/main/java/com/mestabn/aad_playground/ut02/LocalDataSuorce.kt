package com.mestabn.aad_playground.ut02
import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.mestabn.aad_playground.R


class LocalDataSource(private val context: AppCompatActivity) {


    /**
     * Formamos el sharedPreferences, le damos el nombre del key, implementado en strings.xml
     */
    private val sharedPref = context.getSharedPreferences(
        context.getString(R.string.preference_file_key), Context.MODE_PRIVATE
    )

    val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    /**
     * Min sdk 23
     */
    private val encryptSharedPref = EncryptedSharedPreferences.create(
        context,
        context.getString(R.string.preference_encrypt_file_key),
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )


    /**
     * Metodo que guarda mediante serializacion una clave y su contenido
     * mediante el metodo put, lo guardalos y con aplly confirma los cambios
     * Puedes accerder desde cualquier parte de la app al sharedpreferences
     */

    fun saveAsync(key: String, data: String) {
        val edit = sharedPref.edit() //llama al editor sharedPreferences
        edit.putString(key, data) //establece un valor String o nulo en el sharedPreferences
        edit.apply()
    }

    /**
     *Realiza lo mismo que el metodo anterior pero de forma sincrona
     * es decir se puede acceder a la informacion solo desde el hilo donde se crea
     */

    fun saveSync(key: String, data: String) {
        val edit = sharedPref.edit()
        edit.putString(key, data)
        edit.commit()
    }

    /**
     * Forma mas funcional de guardar en el editor
     *
     */
    fun shortSaveAsync(key: String, data: String) {
        with(sharedPref.edit()) {
            putString(key, data)
            apply()
        }
    }

    /**
     * Lee el contenido tomando la clave del sharedpreference mediante el metodo get, devolvemos un String
     */
    fun read(key: String): String? {
        return sharedPref.getString(key, "valor_por_defecto")
    }

    /**
     * Realizamos los metodos anteriores de forma encriptada
     */
    fun saveAsyncEncrypt(key: String, data: String) {
        val edit = encryptSharedPref.edit()
        edit.putString(key, data)
        edit.apply()
    }

    fun saveSyncEncrypt(key: String, data: String) {
        val edit = encryptSharedPref.edit()
        edit.putString(key, data)
        edit.commit()
    }

    fun readEncrypt(key: String): String? {
        return encryptSharedPref.getString(key, "valor_por_defecto")
    }


}
