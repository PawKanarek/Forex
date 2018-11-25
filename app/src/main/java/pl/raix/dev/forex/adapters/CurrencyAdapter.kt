package pl.raix.dev.forex.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import pl.raix.dev.forex.R
import pl.raix.dev.forex.data.CurrencyModel
import pl.raix.dev.forex.data.CurrencyModelType
import pl.raix.dev.forex.databinding.ListItemCurrencyBinding
import pl.raix.dev.forex.databinding.ListItemCurrencyHeaderBinding

class CurrencyAdapter : ListAdapter<CurrencyModel, RecyclerView.ViewHolder>(CurrencyDiffCallback()) {
    companion object {
        private const val TAG = "CurrencyAdapter"
    }

    var loadMore: () -> Unit = {}

    override fun onBindViewHolder(holderCurrency: RecyclerView.ViewHolder, position: Int) {

        if (position >= itemCount - 1) loadMore()

        val currencyModel = getItem(position)

        when (holderCurrency) {
            is CurrencyViewHolder -> holderCurrency.apply {
                bind(createOnClickListener(currencyModel), currencyModel)
            }

            is CurrencyHeaderViewHolder -> holderCurrency.apply {
                bind(currencyModel)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val currencyModel = getItem(position)

        return if (currencyModel.currencyModelType == CurrencyModelType.Currency) 0 else 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            0 -> CurrencyViewHolder(ListItemCurrencyBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            else -> CurrencyHeaderViewHolder(ListItemCurrencyHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }

    private fun createOnClickListener(currencyModel: CurrencyModel): View.OnClickListener {
        return View.OnClickListener {
            val bundle = bundleOf("currencyModel" to currencyModel)
            it.findNavController().navigate(R.id.action_main_fragment_to_currencyDetailsFragment, bundle)
        }
    }

    class CurrencyViewHolder(
        private val binding: ListItemCurrencyBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: View.OnClickListener, item: CurrencyModel) {
            binding.apply {
                clickListener = listener
                currency = item
                executePendingBindings()
            }
        }
    }

    class CurrencyHeaderViewHolder(
        private val binding: ListItemCurrencyHeaderBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CurrencyModel) {
            binding.apply {
                currency = item
                executePendingBindings()
            }
        }
    }
}