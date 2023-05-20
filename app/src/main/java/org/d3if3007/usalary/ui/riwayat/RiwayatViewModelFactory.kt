package org.d3if3007.usalary.ui.riwayat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if3007.usalary.db.GajiDao

class RiwayatViewModelFactory (
    private val db: GajiDao
        ) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RiwayatViewModel::class.java)) {
            return RiwayatViewModel(db) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}