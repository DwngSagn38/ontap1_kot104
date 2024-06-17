package com.example.tutorthi.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Xehoi")
data class XeHoiModel(
    @PrimaryKey(autoGenerate = true) var uid: Int = 0,
    @ColumnInfo(name = "ph42693_name") var ph42693_name : String?,
    @ColumnInfo(name = "ph42693_price") var ph42693_price : Float?,
    @ColumnInfo(name = "model_ph42693") var model_ph42693 : String?,
    @ColumnInfo(name = "status_ph42693") var status_ph42693 : Boolean?,
//    @ColumnInfo(name = "image") var image : String?,
)
