package pl.raix.dev.forex.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders

import pl.raix.dev.forex.R
import pl.raix.dev.forex.viewmodels.CurrencyViewModel

class CurrencyDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = CurrencyDetailsFragment()
    }

    private lateinit var viewModel: CurrencyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.currency_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CurrencyViewModel::class.java)
        // TODO: Use the ViewModel
    }




}
