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
        val pokok = binding.gajiPokokInp.text.toString()
        if(TextUtils.isEmpty(pokok)){
            Toast.makeText(this, R.string.pokok_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val bonus = binding.bonusGajiInp.text.toString()
        if(TextUtils.isEmpty(bonus)) {
            Toast.makeText(this, R.string.bonus_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val gaji = pokok.toFloat() + bonus.toFloat()
        binding.textView5.text = getString(R.string.gaji_x, gaji)
    }
}