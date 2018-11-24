package pl.raix.dev.forex.fragments

import HttpManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.android.volley.Response
import pl.raix.dev.forex.adapters.CurrencyAdapter
import pl.raix.dev.forex.databinding.MainFragmentBinding
import pl.raix.dev.forex.viewmodels.MainViewModel
import java.util.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
        private const val TAG = "MainFragment"
    }

    private lateinit var viewModel: MainViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        val adapter = CurrencyAdapter()
        adapter.loadMore = { loadMore() }

        val binding = MainFragmentBinding.inflate(inflater, container, false)
        binding.forexHistoricalList.adapter = adapter

        viewModel.getCurrency().observe(this, androidx.lifecycle.Observer { newList ->
            if (newList != null) {
                adapter.submitList(newList)
                adapter.notifyDataSetChanged()
            }
        })

        getHistoricalRates(viewModel.currentDate)

        return binding.root
    }

    fun loadMore() {
        if (viewModel.isDataLoading)
            return

        val calendar = Calendar.getInstance()
        calendar.time = viewModel.currentDate
        calendar.add(Calendar.DAY_OF_MONTH, -1)
        getHistoricalRates(calendar.time)
    }

    fun getHistoricalRates(date: Date) {
        viewModel.isDataLoading = true

        HttpManager.getInstance(context!!).getHistorical(date,
            Response.Listener { response ->
                viewModel.isDataLoading = false

                // TODO: use paging component from android-jetpack to avoid mutableList and notifyDataSetChanged in ViewModel observer
                val currentData = viewModel.getCurrency().value
                if (currentData == null) {
                    viewModel.getCurrency().value = response.currencyList.toMutableList()
                } else {
                    currentData.addAll(response.currencyList)
                    viewModel.getCurrency().value = currentData
                }

                viewModel.currentDate = date

            }, Response.ErrorListener { error ->
                viewModel.isDataLoading = false

                Log.d(MainFragment.TAG, "error: %s".format(error.toString()))
            })
    }
}

