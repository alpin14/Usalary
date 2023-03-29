package org.d3if3007.usalary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import org.d3if3007.usalary.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener { hitungGaji() }
    }
    private fun hitungGaji() {
        val pokok = binding.gajiEditText.text.toString()
        if(TextUtils.isEmpty(pokok)){
            Toast.makeText(this, R.string.pokok_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val selectedId = binding.radioGroup.checkedRadioButtonId
        if (selectedId == -1){
            Toast.makeText(this, R.string.bonus_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val bonus = when (binding.radioGroup.checkedRadioButtonId){
            R.id.radioButton100->1000000
            R.id.radioButtonLebih50->500000
            R.id.radioButtonKurang50->250000
            else->0
        }
        val gaji = pokok.toFloat() + bonus
        binding.textView5.text = getString(R.string.gaji_x, gaji)
    }
}