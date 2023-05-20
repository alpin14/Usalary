package org.d3if3007.usalary.ui.hitung

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import org.d3if3007.usalary.R
import org.d3if3007.usalary.databinding.FragmentHitungBinding
import org.d3if3007.usalary.db.GajiDb
import org.d3if3007.usalary.model.TotalGaji

class HitungFragment : Fragment() {
    private lateinit var binding: FragmentHitungBinding

    private val viewModel: HitungViewModel by lazy {
        val db = GajiDb.getInstance(requireContext())
        val factory = HitungViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[HitungViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentHitungBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.button.setOnClickListener { hitungGaji() }
        binding.saranButton.setOnClickListener {
            it.findNavController().navigate(
                R.id.action_hitungFragment_to_saranFragment
            )
        }
        binding.shareButton.setOnClickListener { shareData() }
        viewModel.getTotalGaji().observe(requireActivity(), { showResult(it) })

        viewModel.data.observe(viewLifecycleOwner, {
            if (it == null) return@observe
            Log.d("HitungFragment", "Data tersimpan. ID = ${it.id}")
        })
    }
    private fun shareData() {
        val message = getString(R.string.bagikan_template,
            binding.gajiPokokInp.text,
            binding.bonusGajiInp.text,
            binding.textView5.text,
        )
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, message)
        if (shareIntent.resolveActivity(
                requireActivity().packageManager) != null) {
            startActivity(shareIntent)
        }
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
        binding.buttonGroup.visibility = View.VISIBLE
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_about) {
            findNavController().navigate(
                R.id.action_hitungFragment_to_aboutFragment)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}