package org.d3if3007.usalary.model

import org.d3if3007.usalary.db.GajiEntity

fun GajiEntity.hitungGaji(): TotalGaji{
    val gaji = pokok + bonus
    return TotalGaji(gaji)
}