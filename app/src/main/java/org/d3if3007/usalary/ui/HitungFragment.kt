package org.d3if3007.usalary.ui

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import org.d3if3007.usalary.R
import org.d3if3007.usalary.databinding.FragmentHitungBinding
import org.d3if3007.usalary.model.TotalGaji

class HitungFragment : Fragment() {
    private lateinit var binding: FragmentHitungBinding

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentHitungBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.button.setOnClickListener { hitungGaji() }
        binding.saranButton.setOnClickListener {
            it.findNavController().navigate(
                R.id.action_hitungFragment_to_saranFragment
            )
        }
        viewModel.getTotalGaji().observe(requireActivity(), { showResult(it) })
    }
    private fun hitungGaji() {
        val pokok = binding.gajiPokokInp.text.toString()
        if(TextUtils.isEmpty(pokok)){
            Toast.makeText(context, R.string.pokok_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val bonus = binding.bonusGajiInp.text.toString()
        if(TextUtils.isEmpty(bonus)) {
            Toast.makeText(context, R.string.bonus_invalid, Toast.LENGTH_LONG).show()
            return
        }
        viewModel.hitungGaji(
            pokok.toFloat(),
            bonus.toFloat()
        )
    }

    private fun showResult(result: TotalGaji?){
        if (result == null) return
        binding.textView5.text = getString(R.string.gaji_x, result.gaji)
        binding.saranButton.visibility = View.VISIBLE
    }
}