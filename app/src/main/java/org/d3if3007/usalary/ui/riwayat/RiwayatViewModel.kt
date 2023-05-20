package org.d3if3007.usalary.ui.riwayat

import androidx.lifecycle.ViewModel
import org.d3if3007.usalary.db.GajiDao

class RiwayatViewModel(db: GajiDao) : ViewModel() {
    val data = db.getLastGaji()
}