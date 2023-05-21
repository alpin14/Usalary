package org.d3if3007.usalary.ui.riwayat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.d3if3007.usalary.R
import org.d3if3007.usalary.databinding.ItemRiwayatBinding
import org.d3if3007.usalary.db.GajiEntity
import org.d3if3007.usalary.model.hitungGaji
import java.text.SimpleDateFormat
import java.util.*


class RiwayatAdapter :
        ListAdapter<GajiEntity, RiwayatAdapter.ViewHolder>(DIFF_CALLBACK){

            companion object {
                private val DIFF_CALLBACK =
                    object : DiffUtil.ItemCallback<GajiEntity>(){
                        override fun areItemsTheSame(
                            oldData: GajiEntity,
                            newData: GajiEntity
                        ): Boolean {
                            return oldData.id == newData.id
                        }

                        override fun areContentsTheSame(
                            oldData: GajiEntity,
                            newData: GajiEntity
                        ): Boolean {
                            return oldData == newData
                        }
                    }
            }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ) : ViewHolder{
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRiwayatBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ItemRiwayatBinding
    ) : RecyclerView.ViewHolder(binding.root){

        private val dateFormatter = SimpleDateFormat("dd MMMM yyyy",
        Locale("id", "ID")
        )

        fun bind(item: GajiEntity) = with(binding) {
            val totalGaji = item.hitungGaji()
            tanggalTextView.text = dateFormatter.format(Date(item.tanggal))
            dataTextView.text = root.context.getString(R.string.data_x, item.pokok, item.bonus)
            totalTextView.text= root.context.getString(R.string.hasil_x, totalGaji.gaji)
        }
    }
        }