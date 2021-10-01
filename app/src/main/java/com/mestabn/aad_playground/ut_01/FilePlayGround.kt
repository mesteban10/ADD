package com.mesteban.aad_playground.ut_01

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import kotlin.io.path.Path
import kotlin.io.path.createDirectory
import kotlin.io.path.deleteIfExists
import kotlin.io.path.exists


/**
 * constructor por defecto->()
 * una clase se compone por: estados comportamiento
 */

class FilePlayGround(private val activity: AppCompatActivity) {

    /**
     * funcion que se ejecuta al instanciar la clase(crear un objeto)
     *
     */
    init {
        //createFile()
        // writeFile()
        //readFile()
        //appendText()
        //pendTextWithNewLine()
        //readLineByLine()
        //deleteFile()
        createFolder()

    }

    /*
    fun createFile(){
        val file= File(activity.filesDir,"add.txt") //Ruta interna donde se instala la aplicacion y nombre del fichero
        Log.d("@dev",activity.filesDir.absolutePath) //Crea mensajes que se muestran en Logcat
    }
    */

    fun writeFile() {
        val file = File(activity.filesDir, "add.txt") //Ruta interna donde se instala la aplicacion y nombre del fichero
        file.writeText("hola acceso a datos") //Crea mensajes que se muestran en Logcat
    }

    fun readFile() {
        val file = File(activity.filesDir, "add.txt") //Ruta interna donde se instala la aplicacion y nombre del fichero
        val line = file.readText() //Leo el texto y guardo en variable
        Log.d("@dev", line)
    }


    /*
    Metodo que añade una linea al fichero
     */

    fun appendText(){
        val file= File(activity.filesDir,"add.txt") //Ruta interna donde se instala la aplicacion y nombre del fichero
        file.appendText("Hola2") //metodo que añade linea
        file.appendText("Hola3")
        file.appendText("Hola4")
        val line = file.readText()
        Log.d("@dev",line)
    }

    /*
    añadir texto en nueva linea
     */

    fun appendTextWithNewLine() {
        val file = File(activity.filesDir, "add.txt") //Ruta interna donde se instala la aplicacion y nombre del fichero
        file.appendText("\n") //metodo que añade linea
        file.appendText("Adios1")
        file.appendText("\n")
        file.appendText("Adios2")
        val line = file.readText()
        Log.d("@dev", line)
    }


    //Leer linea por linea
    fun readLineByLine(){
        val file = File(activity.filesDir, "add.txt")
        file.writeText("Linea 1")
        file.appendText("\n")
        file.appendText("Linea 2")
        file.appendText("\n")
        file.appendText("Linea 3")

        val lines : List<String> = file.readLines()
        //froma de recorrer un listado de objetos -->forEach
        lines.forEach{
            Log.d("@dev", it)
        }



    }

    fun deleteFile(){
        val file = File(activity.filesDir, "add.txt")
        if(file.exists()){
            file.delete()
        }
    }

        /**
         * Guarda un array de strings en un fichero
         */
    fun saveToFile(colors:MutableList<String>){
        val file = File(activity.filesDir, "colors.txt")
            if(file.exists()){
                file.writeText("") //Escribo fichero desde cero, vacio
            }

        //voy metiendo los colores en el fichero
        colors.forEach {
            file.appendText("$it\n") //color
        }

    }

    /**
     * Funcion que lee un fichero y guarda los colores
     */

    fun readFromFile(): MutableList<String> {
        val file = File(activity.filesDir, "colors.txt")

        return if (file.exists()){
            file.readLines().toMutableList()
        }else
            mutableListOf()
        }


    /**
     * Funcion para crear carpetas
     */

    fun createFolder(){
        //Opcion 1
        val file = File(activity.filesDir, "/docs")
        file.mkdir()
    }

    fun createFileInFolder(){
        val file = File(activity.filesDir.canonicalPath, "/documents")
        file.writeText("Hola!")
        //file.createNewFile()
    }



}





