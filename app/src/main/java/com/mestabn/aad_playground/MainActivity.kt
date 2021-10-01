package com.mestabn.aad_playground

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.mestabn.aad_playground.ut_01.DataStorageType
import com.mesteban.aad_playground.ut_01.FilePlayGround

class MainActivity : AppCompatActivity() {

    //Listado de string

    val colors : MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        initColors()
        val filePlayGround = FilePlayGround(this)

        filePlayGround.saveToFile(colors)

        val colors = filePlayGround.readFromFile()
        colors.forEach {
            Log.d("@dev", it)
        }



        val dataStorageType = DataStorageType(this)
        //dataStorageType.privateFile()
        //dataStorageType.privateFileCache()
        //dataStorageType.privateExternalFile ()
        dataStorageType.privateExternalCacheFile()
    }

    private fun initColors(){
        colors.add("Red")
        colors.add("Blue")
        colors.add("Yellow")
        colors.add("Pink")
    }
}