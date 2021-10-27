package com.mestabn.aad_playground.ut02

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.mestabn.aad_playground.R
import com.mestabn.aad_playground.ut02.repository.MemDataSource
import com.mestabn.aad_playground.ut02.repository.SharPrefDataSource
import com.mestabn.aad_playground.ut02.repository.UserRepository

class SharedPreferencesActivity : AppCompatActivity() {

    private val TAG = SharedPreferencesActivity::class.java.canonicalName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_preferences)
        saveAsyncSharedpref()
        saveAsyncEncryptSharedpref()
        saveAndFetchUsers()
    }

    private fun saveAsyncSharedpref() {
        val localDataSource = LocalDataSource(this)
        localDataSource.saveAsync("1", "Hola1")
        val data = localDataSource.read("1")
        Log.d(TAG, data!!)
    }

    private fun saveAsyncEncryptSharedpref() {
        val localDataSource = LocalDataSource(this)
        localDataSource.saveAsyncEncrypt("1", "Hola1")
        val data = localDataSource.readEncrypt("1")
        Log.d(TAG, data!!)
    }

    private fun saveAndFetchUsers() {
        val userRepository =
            UserRepository(MemDataSource(), SharPrefDataSource(this))

        val userModel1 = UserModel(1, "User1", "Surname1")
        val userModel2 = UserModel(2, "User2", "Surname2")
        val userModel3 = UserModel(3, "User3", "Surname3")

        //Guardo los usuarios ¿Dónde? en esta clase no se preocupa de eso, no tiene esa responsabilidad.
        userRepository.saveUsers(mutableListOf(userModel1, userModel2, userModel3))

        //Obtengo todos los resultados. ¿Desde donde?. No es responsabilidad de esta clase.
        val users = userRepository.fetchAllUsers()

        //Busco un usuario. ¿Cómo lo he hecho? No es responsabilidad de la vista.
        val user = userRepository.fetchUser(1)

    }

}