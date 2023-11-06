package com.goldlepre.android_scv_import_export.models.excel

import android.app.Application
import androidx.lifecycle.LiveData
import com.goldlepre.android_scv_import_export.Utils.doOnBackground

class ExcelRepository (application: Application) {

    private var excelDao: ExcelDao
    private var addData: LiveData<List<ExcelData>>

    private val database = ExcelDatabase.getInstance(application)

    init {
        excelDao = database.excelDao()
        addData = excelDao.getAllExcelData()
    }

    fun insert(note: ExcelData) {
        doOnBackground {
            excelDao.insert(note)
        }
    }

    fun update(note: ExcelData) {
        doOnBackground {
            excelDao.update(note)
        }
    }

    fun delete(note: ExcelData) {
        doOnBackground {
            excelDao.delete(note)
        }
    }

    fun deleteAllExcelData() {
        doOnBackground {
            excelDao.deleteAllExcelData()
        }
    }

    fun getAllExcelData(): LiveData<List<ExcelData>> {
        return addData
    }



}