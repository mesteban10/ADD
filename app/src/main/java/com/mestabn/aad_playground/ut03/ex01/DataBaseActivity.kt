package com.mestabn.aad_playground.ut03.ex01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.room.Room
import com.mestabn.aad_playground.R
import com.mestabn.aad_playground.ut03.ex01.data.AppDataBase
import com.mestabn.aad_playground.ut03.ex01.data.UserEntity

class DataBaseActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_base)
        initDb()
    }

    private fun initDb() {
        val db = Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java,
            "database-name"
        ).build()

        Thread(Runnable{
            var user = db.userDao().findById(1)
            if (user == null) {
                //Si no existe el usuario, lo inserto
                db.userDao().insert(UserEntity(1, "Alumno1", "alumno1", "alumno@email.es"))
                user = db.userDao().findById(1)
            }
            Log.d("@dev", "$user")
        }).start()
    }

}