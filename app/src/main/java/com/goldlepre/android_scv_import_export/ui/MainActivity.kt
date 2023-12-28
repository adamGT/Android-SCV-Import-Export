package com.goldlepre.android_scv_import_export.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.goldlepre.android_scv_import_export.R
import com.goldlepre.android_scv_import_export.adapters.MainListAdapter
import com.goldlepre.android_scv_import_export.databinding.ActivityMainBinding
import com.goldlepre.android_scv_import_export.models.UserData
import com.goldlepre.android_scv_import_export.models.excel.ExcelData
import com.goldlepre.android_scv_import_export.models.excel.ExcelRepository
import java.io.InputStream


class MainActivity : AppCompatActivity() {

    private val PICKFILE_REQUEST_CODE = 109
    private val fm = supportFragmentManager

    private lateinit var binder: ActivityMainBinding
    private lateinit var rep: ExcelRepository
    private var classTitle = "Empty"
    private var classInstructor = "Empty"

    private var detailFragment: DetailFragment = DetailFragment.newInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binder = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binder.root)

        rep = ExcelRepository(application)
        val adapter = MainListAdapter()
        rep.getAllExcelData().observe(this) {
            adapter.updateList(it)
        }

        binder.apply {
            listRecyclerview.layoutManager = LinearLayoutManager(applicationContext)
            listRecyclerview.setHasFixedSize(true)
            listRecyclerview.adapter = adapter

            adapter.setOnClickListener(object :
                MainListAdapter.OnClickListener {
                override fun onClick(position: Int, data: ExcelData) {
                    fm.beginTransaction().add(R.id.container, detailFragment).addToBackStack("DETAILS").commitAllowingStateLoss()
                    importCsvBtn.visibility = View.INVISIBLE
//                    Toast.makeText(applicationContext, "${data.title} clicked here", Toast.LENGTH_SHORT).show()
                }
            })

            importCsvBtn.setOnClickListener {
                openFilePicker()
            }
        }

    }

    @Suppress("DEPRECATION")
    private fun openFilePicker(){
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.setType("text/comma-separated-values")
        startActivityForResult(intent, PICKFILE_REQUEST_CODE)
    }

    @Deprecated("Deprecated in Java")
    @Suppress("DEPRECATION")
    override fun onActivityResult(
        requestCode: Int, resultCode: Int, resultData: Intent?) {
        super.onActivityResult(requestCode, resultCode, resultData)
        if (requestCode == PICKFILE_REQUEST_CODE
            && resultCode == Activity.RESULT_OK) {

            resultData?.data?.also { uri ->
                val inputStream: InputStream = contentResolver.openInputStream(uri)!!
                val data = readCsv(inputStream)
                if (data.isNotEmpty()) {
                    Log.d("TAGG", data[0].email)

                    //Do My Logic Here

                } else {
                    Toast.makeText(applicationContext, "ERROR WITH THE FILE: \nThe File is Empty",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun readCsv(inputStream: InputStream): List<UserData> {
        var counter = 0
        val reader = inputStream.bufferedReader()
        val header = reader.readLine()
        Log.d("TAGG",header)
        if (header.equals("Name,Email,Phone,ClassName,TeacherName")) {
            return reader.lineSequence()
                .filter { it.isNotBlank() }
                .map {
                    if (counter == 0) {
                        val (name, email, phone, classTitle, teacher) = it.split(
                            ',',
                            ignoreCase = false,
                            limit = 5
                        )
                        this.classTitle = classTitle
                        this.classInstructor = teacher
                    }
                    counter++
                    val (name, email, phone) = it.split(',', ignoreCase = false, limit = 3)
                    UserData(name.trim(), email.trim(), phone.trim())
                }.toList()
        }else{
            Toast.makeText(applicationContext, "ERROR WITH THE FILE: \nThe titles(First line) of the CSV file should be [Name,Email,Phone,ClassName,TeacherName]",Toast.LENGTH_LONG).show()
            return listOf()
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if(fm.backStackEntryCount > 0){
            binder.importCsvBtn.visibility = View.VISIBLE
            fm.popBackStack()
        }else{
            finish()
        }
    }
}