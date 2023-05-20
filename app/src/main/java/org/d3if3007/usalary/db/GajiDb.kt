package org.d3if3007.usalary.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [GajiEntity::class], version = 1, exportSchema = false)
abstract class GajiDb : RoomDatabase(){

    abstract val dao : GajiDao

    companion object{
        @Volatile
        private var INSTANCE : GajiDb? = null

        fun getInstance(context: Context): GajiDb{
            synchronized(this){
                var instance = INSTANCE

                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        GajiDb::class.java,
                        "gaji.db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}