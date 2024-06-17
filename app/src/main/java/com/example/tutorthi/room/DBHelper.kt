package com.example.tutorthi.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [XeHoiModel::class], version = 1)
abstract class DBHelper : RoomDatabase(){
    abstract fun xeHoiDao() : XeHoiDao

    companion object {
        @Volatile
        private var INTANCE : DBHelper? = null

        fun getIntance(context: Context): DBHelper{
            synchronized(this){
                var intance = INTANCE
                if (intance == null) {
                    intance = Room.databaseBuilder(
                        context.applicationContext,
                        DBHelper::class.java,
                        "my_database"
                    ).build()
                }
                return intance
            }
        }
    }
}