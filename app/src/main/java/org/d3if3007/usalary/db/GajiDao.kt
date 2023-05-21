package org.d3if3007.usalary.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GajiDao {
    @Insert
    fun insert(gaji: GajiEntity)

    @Query("SELECT * FROM gaji ORDER BY id DESC")
    fun getLastGaji(): LiveData<List<GajiEntity>>

    @Query("SELECT * FROM gaji ORDER BY id DESC LIMIT 1")
    fun getLastGajiData():LiveData<GajiEntity>

    @Query("DELETE FROM gaji")
    fun deleteAllGaji()

    @Query("DELETE FROM gaji WHERE id = :id")
    fun deleteGaji(id: Long)

    @Query("SELECT * FROM gaji WHERE id = :id")
    fun getGajiById(id: Long): LiveData<GajiEntity>
}