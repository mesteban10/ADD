package com.mestabn.aad_playground.ut02

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.mestabn.aad_playground.R

class SharedPreferencesActivity : AppCompatActivity() {

    private val TAG = SharedPreferencesActivity::class.java.canonicalName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_preferences)
        saveAsyncSharedpref()
        saveAsyncEncryptSharedpref()
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


}