package pl.raix.dev.forex.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders

import pl.raix.dev.forex.data.CurrencyModel
import pl.raix.dev.forex.databinding.CurrencyDetailsFragmentBinding
import pl.raix.dev.forex.viewmodels.CurrencyViewModel

class CurrencyDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = CurrencyDetailsFragment()
    }

    private lateinit var viewModel: CurrencyViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(CurrencyViewModel::class.java)
        val binding = CurrencyDetailsFragmentBinding.inflate(inflater, container, false)
        activity?.title = "Details fragment"
        bindUi(binding)

        return binding.root
    }

    private fun bindUi(binding: CurrencyDetailsFragmentBinding) {
        viewModel.currencyModel.value = arguments?.get("currencyModel") as CurrencyModel
        binding.currencyModel = viewModel.currencyModel.value
    }
}
