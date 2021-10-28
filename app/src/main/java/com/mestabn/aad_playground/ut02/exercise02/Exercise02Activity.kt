package com.mestabn.aad_playground.ut02.exercise02

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.mestabn.aad_playground.R
import com.mestabn.aad_playground.commons.GsonSerializer

/**
 * Actividad que me permite ejecutar una fuente de datos en concreto según el botón pulsado.
 */
class Exercise02Activity : AppCompatActivity() {

    private val TAG: String = Exercise02Activity::class.java.simpleName
    private val factory = LocalStorageFactory<TapaLocalModel>(this, GsonSerializer<TapaLocalModel>())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise02)
        setupView()
    }

    private fun setupView() {
        val actionFile = findViewById<Button>(R.id.action_repository_file)
        actionFile.setOnClickListener {
            runRepository(it.id)
        }

        val actionMemory = findViewById<Button>(R.id.action_repository_mem)
        actionMemory.setOnClickListener {
            runRepository(it.id)
        }

        val actionSharePref = findViewById<Button>(R.id.action_repository_shapref)
        actionSharePref.setOnClickListener {
            runRepository(it.id)
        }
    }

    private fun runRepository(idAction: Int) {
        val dataSource = factory.create(idAction)
        val repository = TapaRepository(dataSource)

        //save action
        repository.save(TapaLocalModel(1, "Tapa1", "Description about tapa1"))

        //fetch action
        val tapa = repository.fetch(1)
        Log.d(TAG, "$tapa")
    }
}