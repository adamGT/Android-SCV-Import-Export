package com.goldlepre.android_scv_import_export.models.excel

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ExcelDao {
    @Insert
    fun insert(excelData: ExcelData)

    @Update
    fun update(excelData: ExcelData)

    @Delete
    fun delete(excelData: ExcelData)

    @Query("delete from excel_data")
    fun deleteAllExcelData()

    @Query("select * from excel_data order by title desc")
    fun getAllExcelData(): LiveData<List<ExcelData>>
}