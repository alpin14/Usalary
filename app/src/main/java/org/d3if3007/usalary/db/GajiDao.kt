package org.d3if3007.usalary.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GajiDao {
    @Insert
    fun insert(gaji: GajiEntity)

    @Query("SELECT * FROM gaji ORDER BY id DESC LIMIT 1")
    fun getLastGaji(): LiveData<GajiEntity?>
}