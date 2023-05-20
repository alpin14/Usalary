package org.d3if3007.usalary.ui.riwayat

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import org.d3if3007.usalary.databinding.FragmentRiwayatBinding
import org.d3if3007.usalary.db.GajiDb

class RiwayatFragment : Fragment() {

    private lateinit var binding: FragmentRiwayatBinding

    private val viewModel: RiwayatViewModel by lazy {
        val db = GajiDb.getInstance(requireContext())
        val factory = RiwayatViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[RiwayatViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRiwayatBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.data.observe(viewLifecycleOwner, {
            Log.d("RiwayatFragment", "Jumlah data: ${it.size}")
        })
    }
}