package org.d3if3007.usalary.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.d3if3007.usalary.model.TotalGaji

class MainViewModel : ViewModel() {

    private val totalGaji = MutableLiveData<TotalGaji?>()

    fun hitungGaji(pokok: Float, bonus: Float) {
        val gaji = pokok + bonus
        totalGaji.value = TotalGaji(gaji)
    }
    fun getTotalGaji(): LiveData<TotalGaji?> = totalGaji
}