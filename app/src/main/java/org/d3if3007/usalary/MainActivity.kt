package org.d3if3007.usalary

import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import org.d3if3007.usalary.databinding.ActivityMainBinding
import org.d3if3007.usalary.model.TotalGaji

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener { hitungGaji() }
        viewModel.getTotalGaji().observe(this, { showResult(it) })
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
        viewModel.hitungGaji(
            pokok.toFloat(),
            bonus.toFloat()
        )
    }

    private fun showResult(result: TotalGaji?){
        if (result == null) return
        binding.textView5.text = getString(R.string.gaji_x, result.gaji)
    }
}