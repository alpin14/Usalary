package org.d3if3007.usalary

import androidx.lifecycle.ViewModel
import org.d3if3007.usalary.model.TotalGaji

class MainViewModel : ViewModel() {

    fun hitungGaji(pokok: Float, bonus: Float): TotalGaji {
        val gaji = pokok + bonus
        return TotalGaji(gaji)
    }
}