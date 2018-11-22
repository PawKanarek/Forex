package pl.raix.dev.forex.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import pl.raix.dev.forex.data.Currency
import pl.raix.dev.forex.databinding.ListItemCurrencyBinding

class CurrencyAdapter : ListAdapter<Currency, CurrencyAdapter.ViewHolder>(CurrencyDiffCallback()) {
    companion object {
        private const val TAG = "CurrencyAdapter"
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currency = getItem(position)
        holder.apply {
            bind(createOnClickListener(currency), currency)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListItemCurrencyBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    private fun createOnClickListener(currency: Currency): View.OnClickListener {
        return View.OnClickListener {
            Log.d(TAG, "currency on click!")
//            it.findNavController().navigate()
        }
    }

    class ViewHolder(
        private val binding: ListItemCurrencyBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: View.OnClickListener, item: Currency) {
            binding.apply {
                clickListener = listener
                curremcy = item
                executePendingBindings()
            }
        }
    }
}