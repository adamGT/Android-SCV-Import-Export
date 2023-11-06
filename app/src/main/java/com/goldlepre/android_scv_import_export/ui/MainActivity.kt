package com.goldlepre.android_scv_import_export.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.goldlepre.android_scv_import_export.adapters.MainListAdapter
import com.goldlepre.android_scv_import_export.databinding.ActivityMainBinding
import com.goldlepre.android_scv_import_export.models.excel.ExcelRepository

class MainActivity : AppCompatActivity() {

    private lateinit var binder: ActivityMainBinding
    private lateinit var rep: ExcelRepository
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


            importCsvBtn.setOnClickListener {

            }
        }

    }
}