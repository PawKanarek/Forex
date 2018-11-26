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
import pl.raix.dev.forex.data.CurrencyModel
import pl.raix.dev.forex.data.CurrencyModelType
import pl.raix.dev.forex.data.HistoricalResponse
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
        val binding = MainFragmentBinding.inflate(inflater, container, false)
        bindUi(binding)

        if (viewModel.getCurrency().value?.isEmpty() != false) {
            getHistoricalRates(viewModel.currentDate)
        }

        return binding.root
    }

    private fun bindUi(binding: MainFragmentBinding) {
        val adapter = CurrencyAdapter()
        adapter.loadMore = { loadMore() }

        binding.forexHistoricalList.adapter = adapter

        viewModel.getCurrency().observe(this, androidx.lifecycle.Observer { newList ->
            if (newList != null) {
                adapter.submitList(newList)
                adapter.notifyDataSetChanged()
            }
        })
    }


    private fun loadMore() {
        if (viewModel.isDataLoading)
            return

        val calendar = Calendar.getInstance()
        calendar.time = viewModel.currentDate
        calendar.add(Calendar.DAY_OF_MONTH, -1)
        getHistoricalRates(calendar.time)
    }

    private fun getHistoricalRates(date: Date) {
        viewModel.isDataLoading = true

        HttpManager.getInstance(context!!).getHistorical(date,
            Response.Listener { response ->
                viewModel.isDataLoading = false

                if (!response.success) {
                    handleError(response)
                    return@Listener
                }

                viewModel.currentDate = date
                if (!response.currencyList.isEmpty()) {
                    val headerModel = CurrencyModel("", 0.0, response.date)
                    headerModel.currencyModelType = CurrencyModelType.Header
                    val currencyList = response.currencyList.toMutableList()
                    currencyList.add(0, headerModel)

                    // TODO: use paging component from android-jetpack
                    val currentViewModelData = viewModel.getCurrency().value
                    if (currentViewModelData == null) {
                        viewModel.getCurrency().value = currencyList
                    } else {
                        currentViewModelData.addAll(currencyList)
                        viewModel.getCurrency().value = currentViewModelData
                    }
                }

            }, Response.ErrorListener { error ->
                viewModel.isDataLoading = false
                Log.d(MainFragment.TAG, "error: %s".format(error.toString()))
            })
    }

    private fun handleError(response: HistoricalResponse) {
        //fast & ugly workaround for bad date problem (sometimes rates from today aren'r generated in fixer portal)
        val code = response.error.getValue("code")
        if (code == "302") {
            loadMore()
        }
    }
}

