package com.example.tutorthi.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface XeHoiDao {
    @Insert
    suspend fun addXe(xe: XeHoiModel)

    @Query("SELECT * FROM Xehoi")
    fun getAll() : Flow<List<XeHoiModel>>

    @Query("SELECT * FROM Xehoi ORDER BY ph42693_price ASC")
    fun sapXepGiaTang() : Flow<List<XeHoiModel>>

    @Delete
    suspend fun deleteXe(xe: XeHoiModel)

    @Update
    suspend fun updateXe(xe: XeHoiModel)
}