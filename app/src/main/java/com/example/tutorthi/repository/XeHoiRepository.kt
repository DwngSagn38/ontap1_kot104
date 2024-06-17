package com.example.tutorthi.repository

import com.example.tutorthi.room.DBHelper
import com.example.tutorthi.room.XeHoiModel

class XeHoiRepository(val dbHelper: DBHelper) {
    suspend fun AddXeToRoom(xe: XeHoiModel){
        dbHelper.xeHoiDao().addXe(xe)
    }

    fun getAll() = dbHelper.xeHoiDao().getAll()


    suspend fun DeleteXeFromRoom(xe: XeHoiModel){
        dbHelper.xeHoiDao().deleteXe(xe)
    }

    suspend fun UpdateXeFromRoom(xe: XeHoiModel){
        dbHelper.xeHoiDao().updateXe(xe)
    }

}