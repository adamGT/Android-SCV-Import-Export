package com.goldlepre.android_scv_import_export.models.excel

import androidx.room.TypeConverter
import com.goldlepre.android_scv_import_export.models.UserData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class DataTypeConverters {
    @TypeConverter
    fun convertUserListToJSONString(userList: List<UserData>): String = Gson().toJson(userList)
    @TypeConverter
    fun convertJSONStringToUserList(jsonString: String): List<UserData> {
        val type: Type = object :TypeToken<List<UserData>>(){}.type
        return Gson().fromJson(jsonString,type)
    }

}