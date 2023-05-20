package org.d3if3007.usalary.ui.hitung

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if3007.usalary.db.GajiDao
import org.d3if3007.usalary.db.GajiEntity
import org.d3if3007.usalary.model.TotalGaji

class HitungViewModel(private val db: GajiDao) : ViewModel() {

    private val totalGaji = MutableLiveData<TotalGaji?>()

    fun hitungGaji(pokok: Float, bonus: Float) {
        val gaji = pokok + bonus
        totalGaji.value = TotalGaji(gaji)

        viewModelScope.launch {
            withContext(Dispatchers. IO){
                val dataGaji = GajiEntity(
                    pokok = pokok,
                    bonus = bonus
                )
                db.insert(dataGaji)
            }
        }
    }
    fun getTotalGaji(): LiveData<TotalGaji?> = totalGaji
}