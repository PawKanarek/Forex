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
        val binding = MainFragmentBinding.inflate(inflater, container, false)

//        val view = inflater.inflate(R.layout.main_fragment, container, false)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        val adapter = CurrencyAdapter()
        binding.forexHistoricalList.adapter = adapter

        viewModel.getCurrency().observe(this, androidx.lifecycle.Observer { newList ->
            if (newList != null) adapter.submitList(newList)
        })

        HttpManager.getInstance(context!!).getHistorical(Calendar.getInstance().time,
            Response.Listener { response ->
                Log.d(MainFragment.TAG, "Response: %s".format(response.toString()))
                viewModel.getCurrency().value = response.rates
            }, Response.ErrorListener { error ->
                Log.d(MainFragment.TAG, "error: %s".format(error.toString()))
            })

        // TODO: Use the ViewModel

        return binding.root
    }
}

