package com.mestabn.aad_playground

import android.os.Bundle
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import com.mestabn.aad_playground.ut_01.exercise01.data.*
import com.mesteban.aad_playground.ut_01.FilePlayGround
import java.io.File

class MainActivity : AppCompatActivity() {

    lateinit var filePlayGround: FilePlayGround
    private val colors: MutableList<String> = mutableListOf()

    lateinit var dataSource: DataSource


    //Ejercicio ficheros
    private lateinit var inputNameFile: AppCompatEditText
    private lateinit var inputContentFile: AppCompatEditText
    private lateinit var actionSave: AppCompatButton
    private lateinit var actionExplorer: AppCompatButton
    private lateinit var actionShowContent: AppCompatButton
    private lateinit var viewerFiles: TextView
    private lateinit var inputNameFileSelected: TextView
    private lateinit var textFileContent: TextView
    private lateinit var actionDeleteFile: AppCompatButton
    private lateinit var optionDataSource: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
    }

    private fun setupView() {
        inputNameFile = findViewById(R.id.input_name_file)
        inputContentFile = findViewById(R.id.input_content_file)
        viewerFiles = findViewById(R.id.viewer_files)
        actionSave = findViewById(R.id.action_save)
        actionSave.setOnClickListener {
            saveFile()
        }
        actionExplorer = findViewById(R.id.action_explorer)
        actionExplorer.setOnClickListener {
            showAllFiles()
        }
        inputNameFileSelected = findViewById(R.id.input_name_file_selected)
        actionShowContent = findViewById(R.id.action_show_content)
        actionShowContent.setOnClickListener {
            showFileContent()
        }
        textFileContent = findViewById(R.id.text_file_content)
        actionDeleteFile = findViewById(R.id.action_delete_file)
        actionDeleteFile.setOnClickListener {
            deleteFile()
        }
        optionDataSource = findViewById(R.id.option_data_source)
        optionDataSource.setOnCheckedChangeListener { group, checkedId ->
            run {
                dataSource = if (checkedId == R.id.option_file) {
                    FileDataSource(filesDir.absolutePath + File.separator + "/exercise01")
                } else {
                    MemDataSource()
                }
            }
        }
    }

    /* private fun exerciseFiles() {
         //save a list of string in a file.
         initColors()
         filePlayGround = FilePlayGround(this)
         filePlayGround.createFolder()

         //filePlayGround.saveToFile(colors)
         //fetch a list of string from a file
         //val colorsFromFile = filePlayGround.readFromFile()
         //colorsFromFile.forEach { Log.d("@dev", it) }

     }

     private fun initColors() {
         colors.add("Red")
         colors.add("Blue")
         colors.add("Navy Blue")
         colors.add("Orange")
     }



     private fun exerciseDataStorageType() {
         val dataStorageType = DataStorageType(this)
         dataStorageType.privateCacheFile()
         //dataStorageType.privateExternalFile()
     }*/


    private fun saveFile() {
        dataSource.save(
            inputNameFile.text.toString(),
            inputContentFile.text.toString()
        )
        resetInputs()
    }

    private fun resetInputs() {
        inputNameFile.text = null
        inputContentFile.text = null
        inputNameFileSelected.text = null
        textFileContent.text = null
    }

    private fun showAllFiles() {
        viewerFiles.text = ""
        dataSource.showAll().forEach {
            viewerFiles.text = "${viewerFiles.text} \n $it"
        }
    }

    private fun showFileContent() {
        textFileContent.text = dataSource.show(inputNameFileSelected.text.toString())
    }

    private fun deleteFile() {
        dataSource.delete(inputNameFileSelected.text.toString())
        showAllFiles()
        resetInputs()
    }
}






