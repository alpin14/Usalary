package org.d3if3007.usalary.ui.riwayat

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.d3if3007.usalary.R
import org.d3if3007.usalary.databinding.FragmentRiwayatBinding
import org.d3if3007.usalary.db.GajiDb


class RiwayatFragment : Fragment() {

    private lateinit var binding: FragmentRiwayatBinding
    private lateinit var myAdapter: RiwayatAdapter
    private lateinit var riwayatDataStore: RiwayatDataStore

    companion object{
        private var riwayatDataStoreInstance: RiwayatDataStore?= null

        fun getRiwayatDataStore(context: Context):RiwayatDataStore{
            if (riwayatDataStoreInstance == null){
                riwayatDataStoreInstance = RiwayatDataStore(context)
            }
            return riwayatDataStoreInstance!!
        }
    }

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
        riwayatDataStore = getRiwayatDataStore(requireContext())
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.riwayat_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_switch_layout -> {
                val currentLayoutManager = binding.recyclerView.layoutManager
                if (currentLayoutManager is GridLayoutManager) {
                    binding.recyclerView.layoutManager = LinearLayoutManager(context)
                    lifecycleScope.launch {
                        riwayatDataStore.saveToDataStore("linear")
                    }
                } else {
                    binding.recyclerView.layoutManager = GridLayoutManager(context, 2)
                    lifecycleScope.launch {
                        riwayatDataStore.saveToDataStore("grid")
                    }
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myAdapter = RiwayatAdapter()
        setHasOptionsMenu(true)
        with(binding.recyclerView){
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
            adapter = myAdapter
            setHasFixedSize(true)
        }

        viewModel.data.observe(viewLifecycleOwner, {
            binding.emptyView.visibility = if (it.isEmpty())
                View.VISIBLE else View.GONE
            myAdapter.submitList(it)
        })

        viewLifecycleOwner.lifecycleScope.launch {
            riwayatDataStore.getFromDataStore().collectLatest { prefs ->
                updateLayoutManager(prefs)
            }
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(
                context, LinearLayoutManager.VERTICAL,
                false)
            this.adapter = myAdapter
            setHasFixedSize(true)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            riwayatDataStore.getFromDataStore().collectLatest { prefs ->
                updateLayoutManager(prefs)
            }
        }
    }

    override fun onStop() {
        super.onStop()

        // Simpan preferensi layout ke DataStore
        lifecycleScope.launch {
            riwayatDataStore.saveToDataStore(getCurrentLayoutManager())
        }
    }

    fun getCurrentLayoutManager(): String {
        val layoutManager = binding.recyclerView.layoutManager
        return if (layoutManager is GridLayoutManager) {
            "grid"
        } else {
            "linear"
        }
    }

    fun updateLayoutManager(layout: String) {
        binding.recyclerView.apply {
            layoutManager = if (layout == "grid") {
                GridLayoutManager(context, 2)
            } else {
                LinearLayoutManager(context)
            }
        }
    }
}