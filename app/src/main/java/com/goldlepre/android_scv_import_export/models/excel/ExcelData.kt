package com.goldlepre.android_scv_import_export.models.excel

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.goldlepre.android_scv_import_export.models.UserData

@Entity(tableName = "excel_data")
class ExcelData (val title: String,
                 val owner: String,
                 val userList: List<UserData>,
                 @PrimaryKey(autoGenerate = false) val id: Int? = null)