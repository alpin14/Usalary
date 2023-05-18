package org.d3if3007.usalary.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import org.d3if3007.usalary.R
import org.d3if3007.usalary.databinding.FragmentSaranBinding
import org.d3if3007.usalary.model.TotalGaji

class SaranFragment: Fragment() {

    private lateinit var binding: FragmentSaranBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = FragmentSaranBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private fun updateUI(gaji: Int) {
        val actionBar = (requireActivity() as AppCompatActivity).supportActionBar
        if (gaji <= 2500000){
            actionBar?.title = getString(R.string.judul_hemat)
            binding.imageView.setImageResource(R.drawable.hemat)
            binding.textView.text = getString(R.string.saran_hemat)
        }
        else{
            actionBar?.title = getString(R.string.judul_boros)
            binding.imageView.setImageResource(R.drawable.boros)
            binding.textView.text = getString(R.string.saran_boros)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        updateUI(gaji = 2600000)
    }
}