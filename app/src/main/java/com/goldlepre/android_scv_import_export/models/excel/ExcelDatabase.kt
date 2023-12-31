package com.goldlepre.android_scv_import_export.models.excel

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.goldlepre.android_scv_import_export.Utils.doOnBackground
import com.goldlepre.android_scv_import_export.models.UserData

@TypeConverters(value = [DataTypeConverters::class])
@Database(entities = [ExcelData::class], version = 1, exportSchema = false)
abstract class ExcelDatabase: RoomDatabase() {

    abstract fun excelDao(): ExcelDao

    companion object {
        private var instance: ExcelDatabase? = null

        @Synchronized
        fun getInstance(ctx: Context): ExcelDatabase {
            if(instance == null)
                instance = Room.databaseBuilder(ctx.applicationContext, ExcelDatabase::class.java,
                    "excel_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build()

            return instance!!

        }

        private val roomCallback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                populateDatabase(instance!!)
            }
        }

        private fun populateDatabase(db: ExcelDatabase) {
            val excelDao = db.excelDao()
            doOnBackground {
//                excelDao.insert(ExcelData("title 1", "desc 1", arrayListOf(UserData("one", "two", "three"))))
//                excelDao.insert(ExcelData("title 2", "desc 2", arrayListOf(UserData("one", "two", "three"))))
//                excelDao.insert(ExcelData("title 3", "desc 3", arrayListOf(UserData("one", "two", "three"))))

            }
        }
    }

}