package org.d3if3007.usalary.ui.riwayat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if3007.usalary.db.GajiDao

class RiwayatViewModel(private val db: GajiDao) : ViewModel() {
    val data = db.getLastGaji()

    fun hapusData(id: Long) = viewModelScope.launch {
        withContext(Dispatchers.IO){
            db.deleteGaji(id)
        }
    }
}