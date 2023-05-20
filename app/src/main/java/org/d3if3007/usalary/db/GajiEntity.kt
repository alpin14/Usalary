package org.d3if3007.usalary.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gaji")
data class GajiEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var tanggal: Long = System.currentTimeMillis(),
    var pokok: Float,
    var bonus: Float
)
